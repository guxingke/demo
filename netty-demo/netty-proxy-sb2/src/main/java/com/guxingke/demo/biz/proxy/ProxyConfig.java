package com.guxingke.demo.biz.proxy;

import com.guxingke.demo.biz.proxy.bootstarp.HttpProxyConfig;
import com.guxingke.demo.biz.proxy.bootstarp.HttpProxyServer;
import com.guxingke.demo.biz.proxy.bootstarp.ProxyConfigHolder;
import com.guxingke.demo.biz.proxy.filter.Filter;
import com.guxingke.demo.biz.proxy.filter.RequestFilter;
import com.guxingke.demo.biz.proxy.filter.ResponseFilter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Configuration
@ComponentScan
public class ProxyConfig implements ApplicationListener {

  @Value("${proxy.port:8080}")
  private int port;

  @Value("${proxy.remote.host}")
  private String remoteHost;

  @Value("${proxy.remote.port}")
  private int remotePort;

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ContextRefreshedEvent) {

      // build config
      HttpProxyConfig config = new HttpProxyConfig(port, remotePort, remoteHost);
      // must exec once
      ProxyConfigHolder.setConfig(config);

      ApplicationContext context = ((ContextRefreshedEvent) event).getApplicationContext();
      Map<String, Filter> beansOfType = context.getBeansOfType(Filter.class);
      System.out.println(beansOfType.size());

      Collection<Filter> filters = beansOfType.values();

      HttpProxyServer.builder()
        .filters(new ArrayList<>(filters))
        .build()
        .start();
    }
  }

  @Bean
  public Filter beforeRequest() {
    return new RequestFilter((request, response) -> {
      // do something
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
    });
  }

  @Bean
  public Filter afterResponse() {
    return new ResponseFilter((request, response) -> {
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
