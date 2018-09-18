package com.gxk.demo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class HttpProxyBackendInitializer extends ChannelInitializer<SocketChannel> {
  private Channel clientChannel;
  public HttpProxyBackendInitializer(Channel channel) {
    this.clientChannel = channel;
  }

  @Override
  protected void initChannel(SocketChannel ch) {
    ch.pipeline().addLast("httpCodec", new HttpClientCodec());
    ch.pipeline().addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024 * 8));
    ch.pipeline().addLast("backendHandler", new HttpBackendHandler(clientChannel));
  }
}
