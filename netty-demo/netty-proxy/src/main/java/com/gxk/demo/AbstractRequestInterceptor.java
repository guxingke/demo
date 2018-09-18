package com.gxk.demo;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public abstract class AbstractRequestInterceptor implements Interceptor{

  @Override
  public void afterResponse(Channel fe, Channel be, FullHttpRequest request, FullHttpResponse response) {
    // do nothing
  }
}
