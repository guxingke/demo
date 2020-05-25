package com.gxk.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TelnetClient {

  public static void main(String[] args) throws InterruptedException {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(new NioEventLoopGroup(2))
        .channel(NioSocketChannel.class);
    bootstrap.handler(new ChannelInitializer() {

      @Override
      protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
            .addLast(new LoggingHandler(LogLevel.INFO))
            .addLast(new TestHandler(channel))
        ;
      }
    });

    ChannelFuture future = bootstrap.connect("127.0.0.1", 20880).sync();
    Channel channel = future.channel();

    byte[] bytes= {13, 10};

    ByteBuf buf = Unpooled.wrappedBuffer(bytes);
    channel.writeAndFlush(buf).sync();

    System.out.println();
  }
}
