package com.gxk.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main{

  public static void main(String[] args) throws IOException {
    byte[] bytes = new AsmTest().test();
    Files.write(Paths.get("Test.class"), bytes);
  }
}
