package com.gxk.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class TestHandler extends ChannelDuplexHandler {

  private Channel channel;

  public TestHandler(Channel channel) {
    this.channel = channel;
  }

  @Override
  public void read(ChannelHandlerContext ctx) throws Exception {
    super.read(ctx);
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
      throws Exception {
    super.write(ctx, msg, promise);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof ByteBuf) {
      ByteBuf buf = (ByteBuf) msg;
      int i = buf.readableBytes();
      byte[] dst = new byte[i];
      buf.readBytes(dst, 0, i);
      System.out.println(new String(dst));
    } else {
      super.channelRead(ctx, msg);
    }
  }
}
