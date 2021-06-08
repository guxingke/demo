package com.gxk.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public final class RedisProxyBoostrap {

  static final int LOCAL_PORT = Integer.parseInt(System.getProperty("localPort", "16379"));
  static final String REMOTE_HOST = System.getProperty("remoteHost", "127.0.0.1");
  static final int REMOTE_PORT = Integer.parseInt(System.getProperty("remotePort", "6379"));

  public static void main(String[] args) throws Exception {
    System.err.println("Proxying *:" + LOCAL_PORT + " to " + REMOTE_HOST + ':' + REMOTE_PORT + " ...");

    // Configure the bootstrap.
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup(8);
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
//          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new RedisProxyInitializer(REMOTE_HOST, REMOTE_PORT))
          .childOption(ChannelOption.AUTO_READ, false)
          .bind(LOCAL_PORT).sync().channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
