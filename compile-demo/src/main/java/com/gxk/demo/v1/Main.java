package com.gxk.demo.v1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class Main {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    final DiagnosticCollector<JavaFileObject> dc = new DiagnosticCollector<>();
    final StandardJavaFileManager manager = compiler
        .getStandardFileManager(dc, Locale.ENGLISH, Charset.defaultCharset());

    final JdkDynamicCompileClassLoader classLoader = new JdkDynamicCompileClassLoader(
        Thread.currentThread().getContextClassLoader());

    final JdkDynamicCompileJavaFileManager fileManager = new JdkDynamicCompileJavaFileManager(
        manager, classLoader);

    final CharSequenceJavaFileObject test = new CharSequenceJavaFileObject("Test",
        "public class Test { public static void v(int x) { "
            + "com.gxk.demo.v1.Test.hello(2); "
            + "try{}finally{}; "
            + "com.gxk.demo.v1.Test2.hello(1); "
            + "com.gxk.demo.v1.Test.hello(java.lang.Math.abs(-12)); "
            + "} }");
    fileManager.addJavaFileObject(StandardLocation.SOURCE_PATH, "", "Test.java", test);

    final String cp = Main.class.getClassLoader().getResource("").getPath();
    List<String> options = new ArrayList<>();
    options.add("-cp");
    options.add(cp);

    final CompilationTask task = compiler
        .getTask(null, fileManager, dc, options, null, Arrays.asList(test));

    final TestProcessor processor = new TestProcessor(new TestScanner());
    task.setProcessors(Arrays.asList(processor));

    final Boolean result = task.call();

    if (!processor.isOk()) {
      return;
    }

    if (result) {
      System.out.println();
    }
    if (!result) {
      for (Diagnostic<? extends JavaFileObject> diagnostic : dc.getDiagnostics()) {
        System.err.println(diagnostic);
      }
      return;
    }

    final Class<?> cls = classLoader.loadClass("Test");

    final Method main = cls.getDeclaredMethod("v", int.class);

    main.invoke(null, 1);
  }
}
