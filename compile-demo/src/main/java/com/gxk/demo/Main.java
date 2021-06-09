package com.gxk.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class Main {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    final StandardJavaFileManager manager = compiler
        .getStandardFileManager(new DiagnosticCollector<>(), Locale.ENGLISH, Charset.defaultCharset());

    final JdkDynamicCompileClassLoader classLoader = new JdkDynamicCompileClassLoader(
        Thread.currentThread().getContextClassLoader());

    final JdkDynamicCompileJavaFileManager fileManager = new JdkDynamicCompileJavaFileManager(
        manager, classLoader);

    final CharSequenceJavaFileObject test = new CharSequenceJavaFileObject("Test",
        "public class Test { public static void v(int x) {System.out.println(\"Hello \" + x);}}");
    fileManager.addJavaFileObject(StandardLocation.SOURCE_PATH, "", "Test.java", test);

    final CompilationTask task = compiler
        .getTask(null, fileManager, new DiagnosticCollector<>(), null, null, Arrays.asList(test));

    final Boolean result = task.call();

    final Class<?> cls = classLoader.loadClass("Test");

    final Method main = cls.getDeclaredMethod("v", int.class);

    main.invoke(null , 1);
    System.out.println(result);

  }
}
