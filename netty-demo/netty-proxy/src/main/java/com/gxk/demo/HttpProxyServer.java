package com.gxk.demo;

import com.gxk.demo.filter.FilterRegistry;
import com.gxk.demo.frontend.HttpProxyFrontendInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;

public class HttpProxyServer {

  public static void main(String[] args) {
    initServer();
  }

  public static void initServer() {
    int port = 8080;

    // init filter
    initFilter();

    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup(1);
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
//          .option(ChannelOption.SO_BACKLOG, 100)
        .handler(new LoggingHandler(LogLevel.DEBUG))
        .childHandler(new HttpProxyFrontendInitializer());

      ChannelFuture f = b
        .bind(port)
        .sync();
      f.channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  private static void initFilter() {
    FilterRegistry registry = FilterRegistry.getInstance();
    registry
      .registerRequestFilter((request, response) -> {
        System.out.println("before request");
        FullHttpRequest req = request.getRequest();
        req.headers().add("test", "req gxk");

        if (req.uri().endsWith("projects/")) {
          ReferenceCountUtil.release(req);
          DefaultFullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
          request.writeAndFlush(resp);
          request.close();
          return;
        }
      })
      .registerResponseFilter((request, response) -> {
        System.out.println("after response2");
        FullHttpResponse resp = response.getResponse();

        resp.headers().add("test", "gxk");

        if (request.getRequest().uri().endsWith("envs/6961/")) {
          return;
        }

        // replace response content
        String fakeContent = "{\"data\": {\"apps\": [\"onepiece\", \"buy-onepiece\", \"vienna-op\", \"mamor\", \"legolas\", \"athena-op\", \"sakura-op\", \"mandala\", \"garuda\", \"terrier\", \"mugger\", \"saturn\", \"tritonis\", \"panama\", \"osgw\", \"dt-notify\", \"munich\", \"buy\", \"japa\", \"vienna\", \"doctor\", \"buy-admin\", \"paris\", \"dva\", \"operator\", \"nginx\"]}}";
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer().writeBytes(fakeContent.getBytes());
        DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(resp.protocolVersion(), resp.status(), byteBuf);

        ReferenceCountUtil.release(resp);
        request.writeAndFlush(fullHttpResponse);
      });
  }
}
