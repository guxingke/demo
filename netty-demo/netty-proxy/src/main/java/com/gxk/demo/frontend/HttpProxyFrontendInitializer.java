package com.gxk.demo.frontend;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpProxyFrontendInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) {
    ch.pipeline().addLast("httpCodec", new HttpServerCodec());
    ch.pipeline().addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024 * 8));
    ch.pipeline().addLast("frontendHandler", new HttpFrontendHandler());
  }
}
