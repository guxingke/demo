package com.gxk.demo.service;

import java.io.Serializable;

public class TestResp implements Serializable {

  private String id;
  private String msg;

  public TestResp(String id, String msg) {
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
