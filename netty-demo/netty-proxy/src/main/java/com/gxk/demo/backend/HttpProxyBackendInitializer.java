package com.gxk.demo.backend;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class HttpProxyBackendInitializer extends ChannelInitializer<SocketChannel> {
  private Channel clientChannel;
  private FullHttpRequest request;

  public HttpProxyBackendInitializer(Channel channel, FullHttpRequest request) {
    this.clientChannel = channel;
    this.request = request;
  }

  @Override
  protected void initChannel(SocketChannel ch) {
//    ch.pipeline().addLast("logger", new LoggingHandler(LogLevel.INFO));
    ch.pipeline().addLast("httpCodec", new HttpClientCodec());
    ch.pipeline().addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024 * 8));
    ch.pipeline().addLast("backendHandler", new HttpBackendHandler(clientChannel, request));
  }
}
