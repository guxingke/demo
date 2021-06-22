package com.gxk.demo.v1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.tools.JavaFileObject;

public class JdkDynamicCompileClassLoader extends ClassLoader {

  public static final String CLASS_EXTENSION = ".class";

  private final Map<String, JavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();

  public JdkDynamicCompileClassLoader(ClassLoader parentClassLoader) {
    super(parentClassLoader);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    JavaFileObject javaFileObject = javaFileObjectMap.get(name);
    if (null != javaFileObject) {
      CharSequenceJavaFileObject charSequenceJavaFileObject = (CharSequenceJavaFileObject) javaFileObject;
      byte[] byteCode = charSequenceJavaFileObject.getByteCode();
      return defineClass(name, byteCode, 0, byteCode.length);
    }
    return super.findClass(name);
  }

  @Override
  public InputStream getResourceAsStream(String name) {
    if (name.endsWith(CLASS_EXTENSION)) {
      String qualifiedClassName = name.substring(0, name.length() - CLASS_EXTENSION.length()).replace('/', '.');
      CharSequenceJavaFileObject javaFileObject = (CharSequenceJavaFileObject) javaFileObjectMap
          .get(qualifiedClassName);
      if (null != javaFileObject && null != javaFileObject.getByteCode()) {
        return new ByteArrayInputStream(javaFileObject.getByteCode());
      }
    }
    return super.getResourceAsStream(name);
  }

  void addJavaFileObject(String qualifiedClassName, JavaFileObject javaFileObject) {
    javaFileObjectMap.put(qualifiedClassName, javaFileObject);
  }

  Collection<JavaFileObject> listJavaFileObject() {
    return Collections.unmodifiableCollection(javaFileObjectMap.values());
  }
}