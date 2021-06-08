package com.gxk.sbd;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test0Application {

  public static void main(String[] args) {
    final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(TestBean.class);

    ctx.refresh();

    final TestBean bean = ctx.getBean(TestBean.class);
    System.out.println(bean.hello());
  }
}
