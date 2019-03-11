package com.gxk.demo.example.client;

import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.route.Dispatcher;

public class HelloServiceProxy implements HelloService {

  private final Dispatcher dispatcher;

  public HelloServiceProxy(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  @Override
  public void hello(String msg) {
    Class<?> inter = this.getClass().getInterfaces()[0];
    dispatcher.invoke(inter, "hello", msg);
  }

  @Override
  public String format(String format, String name) {
    Class<?> inter = this.getClass().getInterfaces()[0];
    Object ret = dispatcher.invoke(inter, "format", format, name);
    return (String) ret;
  }
}
