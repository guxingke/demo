package com.gxk.demo.example.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegistry {
  private static LocalRegistry me = new LocalRegistry();

  private Map<Class<?>, Object> providers;

  private LocalRegistry() {
    this.providers = new HashMap<>();
  }

  public static LocalRegistry getInstance() {
    return me;
  }

  public void register(Class<?> inter, Object impl) {
    providers.putIfAbsent(inter, impl);
  }

  public boolean exist(Class<?> inter) {
    return this.providers.containsKey(inter);
  }

  public Object get(Class<?> inter) {
    return providers.getOrDefault(inter, null);
  }
}
