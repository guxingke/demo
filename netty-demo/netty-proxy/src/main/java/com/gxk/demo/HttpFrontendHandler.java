package com.gxk.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ReferenceCountUtil;

public class HttpFrontendHandler extends ChannelInboundHandlerAdapter {
  private String host = "10.1.4.10";
  private int port = 8000;

  private ChannelFuture cf;
  private AbstractRequestInterceptor requestInterceptor;

  public HttpFrontendHandler() {
    requestInterceptor = new AbstractRequestInterceptor() {
      @Override
      public void beforeRequest(Channel fe, Channel be, FullHttpRequest request) {
        System.out.println("before request");

        System.out.println(request.uri());

        if (request.uri().endsWith("projects/")) {
          ReferenceCountUtil.release(request);
          DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
          fe.writeAndFlush(response);

          fe.close();
          return;
        }

        be.writeAndFlush(request);
      }
    };
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
        requestInterceptor.beforeRequest(channel, cf.channel(), request);
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
}
