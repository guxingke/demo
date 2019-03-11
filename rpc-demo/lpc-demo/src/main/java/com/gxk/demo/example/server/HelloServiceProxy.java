package com.gxk.demo.example.server;

import com.gxk.demo.example.core.HelloService;

public class HelloServiceProxy implements HelloService {

  private final HelloService helloService;

  public HelloServiceProxy(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public void hello(String msg) {
    helloService.hello(msg);
  }

  @Override
  public String format(String format, String name) {
    return helloService.format(format, name);
  }
}
