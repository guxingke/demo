package com.guxingke.demo.biz.proxy.bootstarp;

import com.guxingke.demo.biz.proxy.filter.Filter;
import com.guxingke.demo.biz.proxy.filter.FilterRegistry;
import com.guxingke.demo.biz.proxy.frontend.HttpProxyFrontendInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public class HttpProxyServer {

  private List<Filter> filters;

  public void start() {
    System.out.println("init server");
    HttpProxyConfig config = ProxyConfigHolder.getConfig();

    // init filter
    if (!CollectionUtils.isEmpty(filters)) {
      filters.forEach(it -> {
        FilterRegistry.getInstance().registerFilter(it);
      });
    }

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
        .bind(config.getPort())
        .sync();
      f.channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
