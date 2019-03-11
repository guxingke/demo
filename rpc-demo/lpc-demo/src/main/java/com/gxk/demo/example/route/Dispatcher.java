package com.gxk.demo.example.route;


import com.gxk.demo.example.core.HelloService;

import java.lang.reflect.Method;

public class Dispatcher {

  private final HelloService proxy;

  public Dispatcher(HelloService proxy) {
    this.proxy = proxy;
  }

  public Object invoke(Class<?> inter, String methodName, Object... args) {

    Method[] methods = proxy.getClass().getDeclaredMethods();
    Method method = null;
    for (Method meth : methods) {
      if (meth.getName().equals(methodName)) {
        method = meth;
      }
    }

    if (method == null) {
      System.out.println("err");
      return null;
    }

    try {
      return method.invoke(proxy, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
