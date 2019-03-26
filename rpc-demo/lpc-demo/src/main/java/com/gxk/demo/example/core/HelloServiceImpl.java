package com.gxk.demo.example.core;

public class HelloServiceImpl implements HelloService {

  @Override
  public void hello(String msg) {
    // do nothing
  }

  @Override
  public String format(String format, String name) {
    return String.format(format, name);
  }
}
