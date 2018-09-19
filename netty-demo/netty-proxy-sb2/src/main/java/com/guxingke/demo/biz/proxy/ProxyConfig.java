package com.guxingke.demo.biz.proxy;

import com.guxingke.demo.biz.proxy.bootstarp.HttpProxyConfig;
import com.guxingke.demo.biz.proxy.bootstarp.HttpProxyServer;
import com.guxingke.demo.biz.proxy.bootstarp.ProxyConfigHolder;
import com.guxingke.demo.biz.proxy.filter.Filter;
import com.guxingke.demo.biz.proxy.filter.FilterRegistration;
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
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
      Map<String, FilterRegistration> beansOfType = context.getBeansOfType(FilterRegistration.class);
      System.out.println(beansOfType.size());

      Collection<FilterRegistration> registrations = beansOfType.values();

      List<FilterRegistration> list = new ArrayList<>(registrations);
      list.sort(Comparator.comparing(FilterRegistration::getOrder));
      List<Filter> filters = list.stream()
        .map(FilterRegistration::getFilter)
        .collect(Collectors.toList());

      HttpProxyServer.builder()
        .filters(filters)
        .build()
        .start();
    }
  }

  @Bean
  public FilterRegistration filterRegistration() {
    return FilterRegistration.builder()
      .filter(beforeRequest())
      .order(Integer.MIN_VALUE)
      .path("/*")
      .build();
  }

  @Bean
  public FilterRegistration filterRegistration3() {
    return FilterRegistration.builder()
      .filter(beforeRequest2())
      .order(Integer.MIN_VALUE + 1)
      .path("/*")
      .build();
  }

  @Bean
  public FilterRegistration filterRegistration2() {
    return FilterRegistration.builder()
      .filter(afterResponse())
      .order(Integer.MAX_VALUE)
      .path("/*")
      .build();
  }

  @Bean
  public FilterRegistration filterRegistration4() {
    return FilterRegistration.builder()
      .filter(afterResponse2())
      .order(Integer.MAX_VALUE - 1)
      .path("/*")
      .build();
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
  public Filter beforeRequest2() {
    return new RequestFilter((request, response) -> {
      // do something
      System.out.println("before request2");
      FullHttpRequest req = request.getRequest();
      req.headers().add("test", "req gxk");
    });
  }

  @Bean
  public Filter afterResponse() {
    return new ResponseFilter((request, response) -> {
      System.out.println("after response");
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

  @Bean
  public Filter afterResponse2() {
    return new ResponseFilter((request, response) -> {
      System.out.println("after response2");
      FullHttpResponse resp = response.getResponse();

      resp.headers().add("test1", "gxk2");

      if (request.getRequest().uri().endsWith("envs/")) {
        return;
      }
    });
  }
}
