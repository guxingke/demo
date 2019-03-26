package com.gxk.demo.example;

import com.gxk.demo.example.client.HelloServiceProxy;
import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.example.register.LocalRegistry;

public class Boot {

  public static void main(String[] args) {
    LocalRegistry.getInstance().register(HelloService.class, new HelloServiceImpl());
    HelloServiceProxy clientProxy = new HelloServiceProxy();

    clientProxy.hello("test");

    System.out.println(clientProxy.format("%s_ssssss", "hahahah"));
  }
}
