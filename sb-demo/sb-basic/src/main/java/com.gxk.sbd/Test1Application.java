//package com.gxk.sbd;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class Test1Application {
//
//  public static void main(String[] args) {
//    final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//    ctx.register(TestConfiguration.class);
//
//    ctx.refresh();
//
//    final TestBean2 bean = ctx.getBean(TestBean2.class);
//    System.out.println(bean.hello());
//  }
//}
