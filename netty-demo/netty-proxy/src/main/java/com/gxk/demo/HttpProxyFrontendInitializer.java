package com.gxk.demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpProxyFrontendInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) {
    ch.pipeline().addLast("httpCodec", new HttpServerCodec());
    ch.pipeline().addLast("frontendHandler", new HttpFrontendHandler());
  }
}
