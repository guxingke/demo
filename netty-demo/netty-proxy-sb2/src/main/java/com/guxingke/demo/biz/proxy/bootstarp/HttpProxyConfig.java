package com.guxingke.demo.biz.proxy.bootstarp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpProxyConfig {
  private Integer port;
  private Integer remotePort;
  private String remoteHost;
}
