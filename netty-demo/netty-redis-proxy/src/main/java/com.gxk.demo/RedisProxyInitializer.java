package com.gxk.demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.redis.RedisArrayAggregator;
import io.netty.handler.codec.redis.RedisBulkStringAggregator;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;

public class RedisProxyInitializer extends ChannelInitializer<SocketChannel> {

  private final String remoteHost;
  private final int remotePort;

  public RedisProxyInitializer(String remoteHost, int remotePort) {
    this.remoteHost = remoteHost;
    this.remotePort = remotePort;
  }

  @Override
  public void initChannel(SocketChannel ch) {
    ch.pipeline().addLast(
//                new LoggingHandler(LogLevel.INFO),
        new RedisDecoder(),
        new RedisBulkStringAggregator(),
        new RedisArrayAggregator(),
        new RedisEncoder(),
        new RedisProxyFrontendHandler(remoteHost, remotePort));
  }
}
