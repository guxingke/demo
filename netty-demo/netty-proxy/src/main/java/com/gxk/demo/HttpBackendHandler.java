package com.gxk.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class HttpBackendHandler extends ChannelInboundHandlerAdapter {

  private final Channel clientChannel;

  private String fakeContent = "{\"data\": {\"apps\": [\"onepiece\", \"buy-onepiece\", \"vienna-op\", \"mamor\", \"legolas\", \"athena-op\", \"sakura-op\", \"mandala\", \"garuda\", \"terrier\", \"mugger\", \"saturn\", \"tritonis\", \"panama\", \"osgw\", \"dt-notify\", \"munich\", \"buy\", \"japa\", \"vienna\", \"doctor\", \"buy-admin\", \"paris\", \"dva\", \"operator\", \"nginx\"]}}";

  public HttpBackendHandler(Channel clientChannel) {
    this.clientChannel = clientChannel;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    //客户端channel已关闭则不转发了
    if (!clientChannel.isOpen()) {
      ReferenceCountUtil.release(msg);
      return;
    }
    if (msg instanceof FullHttpResponse) {

      // interceptor
      FullHttpResponse response = (FullHttpResponse) msg;
      response.headers().add("test", "gxk");
      String content = response.content().toString(Charset.forName("UTF-8"));
      System.out.println(content);

      ReferenceCountUtil.release(msg);

      // replace response content
      ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer().writeBytes(fakeContent.getBytes());
      DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(response.protocolVersion(), response.status(), byteBuf);
      clientChannel.writeAndFlush(fullHttpResponse);
    } else {
      System.out.println(msg.getClass());
      clientChannel.writeAndFlush(msg);
    }
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) {
    ctx.channel().close();
    clientChannel.close();
  }
}
