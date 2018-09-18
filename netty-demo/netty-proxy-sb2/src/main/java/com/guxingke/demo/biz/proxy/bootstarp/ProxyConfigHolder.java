package com.guxingke.demo.biz.proxy.bootstarp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyConfigHolder {
  private static HttpProxyConfig target;
  private static boolean init;

  public static void setConfig(HttpProxyConfig config) {
    if (init) {
      log.error("init config err");
      return;
    }
    target = config;
    init = true;
  }

  public static HttpProxyConfig getConfig() {
    if (!init) {
      log.error("config not init");
      return null;
    }
    return target;
  }
}
