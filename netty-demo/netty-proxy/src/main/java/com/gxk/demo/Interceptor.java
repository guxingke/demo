package com.gxk.demo;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;


public interface Interceptor {
  void beforeRequest(Channel fe, Channel be, FullHttpRequest request);

  void afterResponse(Channel fe, Channel be, FullHttpRequest request, FullHttpResponse response);
}
