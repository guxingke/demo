package com.gxk.demo.frontend;

import com.gxk.demo.backend.HttpProxyBackendInitializer;
import com.gxk.demo.filter.DefaultFilterChainFactory;
import com.gxk.demo.filter.FilterChain;
import com.gxk.demo.model.HttpRequest;
import com.gxk.demo.model.HttpResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class HttpFrontendHandler extends ChannelInboundHandlerAdapter {
  private String host = "10.1.4.10";
  private int port = 8000;

  private ChannelFuture cf;

  private FilterChain filterChain;

  public HttpFrontendHandler() {
    this.filterChain = DefaultFilterChainFactory.newFilterChain();
  }

  @Override
  public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
    if (!(msg instanceof FullHttpRequest)) {
      ReferenceCountUtil.release(msg);
      return;
    }
    Channel channel = ctx.channel();

    FullHttpRequest request = (FullHttpRequest) msg;

    ChannelInitializer channelInitializer = new HttpProxyBackendInitializer(channel, request);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(channel.eventLoop())
      .channel(NioSocketChannel.class)
      .handler(channelInitializer);
    cf = bootstrap.connect(host, port);

    cf.addListener(future -> {
      if (future.isSuccess()) {
        HttpRequest httpRequest = new HttpRequest(channel, request);
        HttpResponse httpResponse = new HttpResponse(cf.channel());

        this.filterChain.doFilter(httpRequest, httpResponse);

        if (httpRequest.isProcessDone()) {
          System.out.println("filter process the request");
        } else {
          httpResponse.writeAndFlush(httpRequest.getRequest());
        }

      } else {
        cf.channel().close();
      }
    });
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) {
    if (cf != null) {
      cf.channel().close();
    }
    ctx.channel().close();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    if (cf != null) {
      cf.channel().close();
    }
    ctx.channel().close();
  }

  public static void closeOnFlush(Channel ch) {
    if (ch.isActive()) {
      ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
  }
}
