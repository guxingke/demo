package com.gxk.demo.scdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoGatewayApplication {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    //@formatter:off
    return builder.routes()
      .route("path_route", r -> r.path("/get")
        .uri("http://httpbin.org"))
      .route("host_route", r -> r.host("*.myhost.org")
        .uri("http://httpbin.org"))
      .route("rewrite_route", r -> r.host("*.rewrite.org")
        .filters(f -> f.rewritePath("/foo/(?<segment>.*)",
          "/${segment}"))
        .uri("http://httpbin.org"))
      .route("hystrix_route", r -> r.host("*.hystrix.org")
        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
        .uri("http://httpbin.org"))
      .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
        .uri("http://httpbin.org"))
      .route("websocket_route", r -> r.path("/echo")
        .uri("ws://localhost:9000"))
      .build();
    //@formatter:on
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoGatewayApplication.class, args);
  }
}