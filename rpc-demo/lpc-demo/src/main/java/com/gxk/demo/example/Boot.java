package com.gxk.demo.example;

import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.example.route.Dispatcher;
import com.gxk.demo.example.server.HelloServiceProxy;

public class Boot {

  public static void main(String[] args) {
    Dispatcher dispatcher = new Dispatcher(new HelloServiceProxy(new HelloServiceImpl()));

    com.gxk.demo.example.client.HelloServiceProxy clientProxy = new com.gxk.demo.example.client.HelloServiceProxy(dispatcher);

    clientProxy.hello("test");

    System.out.println(clientProxy.format("%s_ssssss", "hahahah"));
  }
}
