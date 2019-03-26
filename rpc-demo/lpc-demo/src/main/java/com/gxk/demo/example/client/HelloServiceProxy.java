package com.gxk.demo.example.client;

import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.register.LocalRegistry;

public class HelloServiceProxy implements HelloService {
  HelloService helloService;

  public HelloServiceProxy() {
    helloService = (HelloService) LocalRegistry.getInstance().get(HelloService.class);
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
