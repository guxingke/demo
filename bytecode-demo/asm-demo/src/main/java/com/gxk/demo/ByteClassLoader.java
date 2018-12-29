package com.gxk.demo;

public class ByteClassLoader extends ClassLoader {

  protected Class<?> findClass(String name, final byte[] classBytes) {
    return defineClass(name, classBytes, 0, classBytes.length);
  }
}
