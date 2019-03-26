package com.gxk.demo.staticproxy;

import com.gxk.demo.staticproxy.client.HelloServiceProxy;
import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.register.LocalRegistry;

public class Boot {

  public static void main(String[] args) {
    LocalRegistry.getInstance().register(HelloService.class, new HelloServiceImpl());
    HelloServiceProxy clientProxy = new HelloServiceProxy();

    clientProxy.hello("test");

    System.out.println(clientProxy.format("%s_ssssss", "hahahah"));
  }
}
