package com.mng.rpc.server;

public interface HelloService {

  String hello(String msg);

  String format(String format, String name);
}
