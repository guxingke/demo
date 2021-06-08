package com.gxk.sbd;

import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;

public class TestBean2 {

  @Resource
  @Lazy
  private TestBean testBean;

  public String hello() {
    return testBean.hello();
  }
}
