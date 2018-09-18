package com.gxk.demo;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;

public abstract class AbstractResponseInterptor implements Interceptor{

  @Override
  public void beforeRequest(Channel fe, Channel be, FullHttpRequest request) {
    // do nothing
  }
}
