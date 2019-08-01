package com.gxk.demo.service;

import java.io.Serializable;

public class TestReq implements Serializable {

  private String id;
  private String msg;

  public TestReq(String id, String msg) {
    this.id = id;
    this.msg = msg;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
