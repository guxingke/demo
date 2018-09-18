package com.gxk.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class HttpBackendHandler extends ChannelInboundHandlerAdapter {

  private final Channel clientChannel;
  private final FullHttpRequest request;

  private AbstractResponseInterptor responseInterptor;

  private String fakeContent = "{\"data\": {\"apps\": [\"onepiece\", \"buy-onepiece\", \"vienna-op\", \"mamor\", \"legolas\", \"athena-op\", \"sakura-op\", \"mandala\", \"garuda\", \"terrier\", \"mugger\", \"saturn\", \"tritonis\", \"panama\", \"osgw\", \"dt-notify\", \"munich\", \"buy\", \"japa\", \"vienna\", \"doctor\", \"buy-admin\", \"paris\", \"dva\", \"operator\", \"nginx\"]}}";

  public HttpBackendHandler(Channel clientChannel, FullHttpRequest request) {
    this.clientChannel = clientChannel;
    this.request = request;

    responseInterptor = new AbstractResponseInterptor() {
      @Override
      public void afterResponse(Channel fe, Channel be, FullHttpRequest request, FullHttpResponse response) {
        System.out.println("after response");

        response.headers().add("test", "gxk");

        ReferenceCountUtil.release(response);

        // replace response content
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer().writeBytes(fakeContent.getBytes());
        DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(response.protocolVersion(), response.status(), byteBuf);
        fe.writeAndFlush(fullHttpResponse);
      }
    };
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    //客户端channel已关闭则不转发了
    if (!clientChannel.isOpen()) {
      ReferenceCountUtil.release(msg);
      return;
    }

    if (!(msg instanceof FullHttpResponse)) {
      ReferenceCountUtil.release(msg);
      return;
    }

    FullHttpResponse response = (FullHttpResponse) msg;

    // interceptor
    responseInterptor.afterResponse(clientChannel, ctx.channel(), request, response);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) {
    ctx.channel().close();
    clientChannel.close();
  }
}
