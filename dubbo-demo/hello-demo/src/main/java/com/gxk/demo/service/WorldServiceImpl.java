package com.gxk.demo.service;

import org.apache.dubbo.rpc.RpcContext;

public class WorldServiceImpl implements WorldService {

  @Override
  public String world(String name) {
    return "World world" + name + ", response from provider: " + RpcContext.getContext()
        .getLocalAddress();
  }

  @Override
  public String hello(String name) {
    return "World hello" + name + ", response from provider: " + RpcContext.getContext()
        .getLocalAddress();
  }

}
