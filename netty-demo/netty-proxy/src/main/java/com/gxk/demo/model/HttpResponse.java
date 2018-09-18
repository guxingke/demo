package com.gxk.demo.model;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Data;
import lombok.Getter;

@Data
public class HttpResponse {
  private final Channel channel;
  @Getter
  private FullHttpResponse response;

  public HttpResponse(Channel backend) {
    this.channel = backend;
  }

  public HttpResponse(Channel backend, FullHttpResponse response) {
    this.channel = backend;
    this.response = response;
  }

  public void writeAndFlush(FullHttpRequest req) {
    this.channel.writeAndFlush(req);
  }

  public void close() {
    this.channel.close();
  }
}
