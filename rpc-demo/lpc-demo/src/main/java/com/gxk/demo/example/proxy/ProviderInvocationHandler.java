package com.gxk.demo.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProviderInvocationHandler implements InvocationHandler {

  private final Object target;

  public ProviderInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return method.invoke(target, args);
  }
}
