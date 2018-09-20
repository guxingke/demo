package com.gxk.demo.sbwebflux;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class EchoHandler {

  public Mono<ServerResponse> echo(ServerRequest request) {
    return ServerResponse.ok().body(Mono.just(request.uri().toString()), String.class); }
}