package com.guxingke.demo.biz.proxy.backend;

import com.guxingke.demo.biz.proxy.filter.DefaultFilterChainFactory;
import com.guxingke.demo.biz.proxy.filter.FilterChain;
import com.guxingke.demo.biz.proxy.frontend.HttpFrontendHandler;
import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;


public class HttpBackendHandler extends ChannelInboundHandlerAdapter {

  private final Channel clientChannel;
  private final FullHttpRequest request;
  private final FilterChain filterChain;

  public HttpBackendHandler(Channel clientChannel, FullHttpRequest request) {
    this.clientChannel = clientChannel;
    this.request = request;
    this.filterChain = DefaultFilterChainFactory.newFilterChain();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    if (!clientChannel.isOpen()) {
      ReferenceCountUtil.release(msg);
      return;
    }

    if (!(msg instanceof FullHttpResponse)) {
      ReferenceCountUtil.release(msg);
      return;
    }

    FullHttpResponse response = (FullHttpResponse) msg;

    HttpRequest httpRequest = new HttpRequest(clientChannel, this.request);
    HttpResponse httpResponse = new HttpResponse(ctx.channel(), response);

    filterChain.doFilter(httpRequest, httpResponse);
    if (httpRequest.isProcessDone()) {
      System.out.println("response filter process done");
    } else {
      httpRequest.writeAndFlush(response);
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.read();
    ctx.write(Unpooled.EMPTY_BUFFER);
  }


  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    ctx.channel().close();
    HttpFrontendHandler.closeOnFlush(clientChannel);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    HttpFrontendHandler.closeOnFlush(ctx.channel());
  }
}
