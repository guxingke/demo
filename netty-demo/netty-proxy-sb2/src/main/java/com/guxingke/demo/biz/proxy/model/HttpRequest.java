package com.guxingke.demo.biz.proxy.model;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Getter;

public class HttpRequest {
  private final Channel channel;
  @Getter
  private final FullHttpRequest request;
  @Getter
  private boolean processDone;

  public HttpRequest(Channel frontend, FullHttpRequest request) {
    this.channel = frontend;
    this.request = request;
    this.processDone = false;
  }

  public void writeAndFlush(FullHttpResponse resp) {
    this.channel.writeAndFlush(resp);
    this.processDone = true;
  }

  public void close() {
    this.channel.close();
  }
}
