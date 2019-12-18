package com.gxk.demo.pl.hello;

public class ByteClassLoader extends ClassLoader {

  public Class<?> findClass(String name, final byte[] classBytes) {
    return defineClass(name, classBytes, 0, classBytes.length);
  }
}