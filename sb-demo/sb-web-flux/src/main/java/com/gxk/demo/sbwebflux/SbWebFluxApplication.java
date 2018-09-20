package com.gxk.demo.sbwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class SbWebFluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(SbWebFluxApplication.class, args);
  }

  @Bean
  public RouterFunction<ServerResponse> routeFunc(EchoHandler echoHandler) {
    return route(GET("/test"), echoHandler::echo);
  }
}
