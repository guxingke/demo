package com.gxk.demo.service;

import org.apache.dubbo.rpc.RpcContext;

public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(String name) {
    return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
  }

  @Override
  public TestResp hello1(TestReq req) {
    return new TestResp(req.getId(), req.getMsg());
  }
}
