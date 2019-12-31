package com.gxk.demo;

import com.gxk.demo.hybrid.HybridDemo;
import com.gxk.demo.jvm.JvmDemo;
import com.gxk.demo.register.RegisterDemo;
import com.gxk.demo.stack.StackDemo;
import java.io.IOException;

public class Main{

  public static void main(String[] args) throws IOException {
    String demo = "";
    if (args.length >= 1) {
      demo = args[0];
    }

    switch (demo.toUpperCase()) {
      case "REGISTER":
        RegisterDemo registerDemo = new RegisterDemo();
        registerDemo.run();
        return;
      case "STACK":
        StackDemo stackDemo = new StackDemo();
        stackDemo.run();
        return;
      case "HYBRID":
        HybridDemo hybridDemo = new HybridDemo();
        hybridDemo.run();
        return;
      default:
        JvmDemo jvmDemo = new JvmDemo();
        jvmDemo.run(args[1]);
        return;
    }
  }
}
