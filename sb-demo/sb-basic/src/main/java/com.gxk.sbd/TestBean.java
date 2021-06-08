package com.gxk.sbd;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Lazy
public class TestBean {

  @RequestMapping("/")
  public String hello() {
    return "hello";
  }
}
