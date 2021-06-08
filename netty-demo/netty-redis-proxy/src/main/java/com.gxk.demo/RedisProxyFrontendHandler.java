package com.gxk.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.redis.RedisArrayAggregator;
import io.netty.handler.codec.redis.RedisBulkStringAggregator;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;
import io.netty.util.AttributeKey;
import java.net.InetSocketAddress;

public class RedisProxyFrontendHandler extends ChannelInboundHandlerAdapter {

  private final String remoteHost;
  private final int remotePort;

  public static final AttributeKey<Integer> PORT = AttributeKey.newInstance("PORT");

  // As we use inboundChannel.eventLoop() when building the Bootstrap this does not need to be volatile as
  // the outboundChannel will use the same EventLoop (and therefore Thread) as the inboundChannel.
  private Channel outboundChannel;

  public RedisProxyFrontendHandler(String remoteHost, int remotePort) {
    this.remoteHost = remoteHost;
    this.remotePort = remotePort;
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    final Channel inboundChannel = ctx.channel();
    inboundChannel.attr(PORT).set(((InetSocketAddress) inboundChannel.unsafe().remoteAddress()).getPort());

    // Start the connection attempt.
    Bootstrap b = new Bootstrap();
    b.group(inboundChannel.eventLoop())
        .channel(ctx.channel().getClass())
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            final ChannelPipeline p = ch.pipeline();
            p.addLast(
                new RedisDecoder(),
                new RedisBulkStringAggregator(),
                new RedisArrayAggregator(),
                new RedisEncoder(),
                new HexDumpProxyBackendHandler(inboundChannel)
            );
          }
        })
//        .option(ChannelOption.SO_SNDBUF, 32 * 1024 * 1024)
//        .option(ChannelOption.SO_RCVBUF, 32 * 1024 * 1024)
//        .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
        .option(ChannelOption.TCP_NODELAY, false)
//        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .option(ChannelOption.AUTO_READ, false);
    ChannelFuture f = b.connect(remoteHost, remotePort);
    outboundChannel = f.channel();
    f.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        if (future.isSuccess()) {
          // connection complete start to read first data
          inboundChannel.read();
        } else {
          // Close the connection if the connection attempt has failed.
          inboundChannel.close();
        }
      }
    });
  }

  @Override
  public void channelRead(final ChannelHandlerContext ctx, Object msg) {
//    System.out.println(ctx.channel().attr(PORT).get());
//    System.out.println(Thread.currentThread().getId());
    if (outboundChannel.isActive()) {
      outboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) {
          if (future.isSuccess()) {
            // was able to flush out data, start to read the next chunk
            ctx.channel().read();
          } else {
            future.channel().close();
          }
        }
      });
    }
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
//    ctx.channel().attr()
    if (outboundChannel != null) {
      closeOnFlush(outboundChannel);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    closeOnFlush(ctx.channel());
  }

  /**
   * Closes the specified channel after all queued write requests are flushed.
   */
  static void closeOnFlush(Channel ch) {
    if (ch.isActive()) {
      ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
  }
}
