package com.gxk.demo.dynamicproxy.proxy;

import com.gxk.demo.register.LocalRegistry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ConsumerInvocationHandler implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    LocalRegistry registry = LocalRegistry.getInstance();
    Class<?> inter = proxy.getClass().getInterfaces()[0];
    boolean exist = registry.exist(inter);
    if (!exist) {
      throw new RuntimeException("not found service provider");
    }

    Object target = registry.get(inter);
    return method.invoke(target, args);
  }
}
