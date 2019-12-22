package com.gxk.demo;

import com.gxk.demo.hybrid.HybridDemo;
import com.gxk.demo.register.RegisterDemo;
import com.gxk.demo.stack.StackDemo;

public class Main{

  public static void main(String[] args){
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
      default:
        HybridDemo hybridDemo = new HybridDemo();
        hybridDemo.run();
        return;
    }
  }
}
