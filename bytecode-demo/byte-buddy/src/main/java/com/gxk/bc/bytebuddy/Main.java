package com.gxk.bc.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Main {

  public static void main(String[] args) throws Exception {
    Class<?> dynamicType = new ByteBuddy()
      .subclass(Object.class)
      .name("First")
      .defineField("name", String.class, Visibility.PUBLIC)
      .method(ElementMatchers.named("toString"))
      .intercept(FixedValue.value("Hello World!"))
      .make()
      .load(Main.class.getClassLoader())
      .getLoaded();

    Object obj = dynamicType.getDeclaredConstructor().newInstance();

    System.out.println(obj.getClass().getField("name").get(obj));
    System.out.println(obj.toString());
  }
}
