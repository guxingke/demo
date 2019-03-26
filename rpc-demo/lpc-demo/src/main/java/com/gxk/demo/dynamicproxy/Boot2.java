package com.gxk.demo.dynamicproxy;

import com.gxk.demo.example.core.HelloService;
import com.gxk.demo.example.core.HelloServiceImpl;
import com.gxk.demo.dynamicproxy.proxy.ConsumerInvocationHandler;
import com.gxk.demo.dynamicproxy.proxy.ProviderInvocationHandler;
import com.gxk.demo.register.LocalRegistry;

import java.lang.reflect.Proxy;

public class Boot2 {

  public static void main(String[] args) {

    // providers
    HelloService helloService = new HelloServiceImpl();
    Object instance = Proxy.newProxyInstance(Boot2.class.getClassLoader(), new Class[] {HelloService.class}, new ProviderInvocationHandler(helloService));

    // bridge
    LocalRegistry.getInstance().register(HelloService.class, instance);

    // consumer
    HelloService service = (HelloService) Proxy.newProxyInstance(Boot2.class.getClassLoader(), new Class[] {HelloService.class}, new ConsumerInvocationHandler());
    service.hello("test");
  }
}
