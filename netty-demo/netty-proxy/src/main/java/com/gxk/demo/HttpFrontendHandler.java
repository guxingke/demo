package com.gxk.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequest;

public class HttpFrontendHandler extends ChannelInboundHandlerAdapter {
  private String host = "10.1.4.10";
  private int port = 8000;

  private boolean init;
  private ChannelFuture cf;

  @Override
  public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
    Channel channel = ctx.channel();
    if (!init) {
      if (!(msg instanceof HttpRequest)) {
        return;
      }
      initBackend(msg, channel);
      return;
    }
    cf.channel().writeAndFlush(msg);
  }

  private void initBackend(Object msg, Channel channel) {
    ChannelInitializer channelInitializer = new HttpProxyBackendInitializer(channel);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(channel.eventLoop())
      .channel(NioSocketChannel.class)
      .handler(channelInitializer);
    cf = bootstrap.connect(host, port);
    cf.addListener((ChannelFutureListener) future -> {
      if (future.isSuccess()) {
        future.channel().writeAndFlush(msg);
      } else {
        future.channel().close();
        channel.close();
      }
    });
    init = true;
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
