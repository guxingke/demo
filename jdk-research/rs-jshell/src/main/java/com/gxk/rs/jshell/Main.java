package com.gxk.rs.jshell;

import jdk.jshell.tool.JavaShellToolBuilder;

import java.util.Locale;

public class Main {

  public static void main(String[] args) throws Exception {
    // always use en.
    Locale.setDefault(Locale.ENGLISH);

    int code = JavaShellToolBuilder
      .builder()
      .start(args);
    System.exit(code);
  }
}
