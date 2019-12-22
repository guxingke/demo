package com.gxk.demo.register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterDemo {
  public static final Map<Integer, Integer> REGISTER = new HashMap<>();

  public static void main(String[] args) {
    new RegisterDemo().run();
  }

  public void run() {
    // 1. 构造指令
    List<Inst> insts = genInsts();

    int size = insts.size();
    int pc = 0;

    while (pc < size) {
      Inst inst = insts.get(pc);
      inst.execute();
      pc++;
    }
  }

  private List<Inst> genInsts() {
//    mov &0 1        // {0:1}
//    mov &1 1        // {0:1, 1:1}
//    add &2 &0 &1    // {0:1, 1:1, 2:2}
//
//    mov &3 2        // {0:1, 1:1, 2:2, 3:2}
//    mov &4 2        // {0:1, 1:1, 2:2, 3:2, 4:2}
//    add &5 &3 &4    // {0:1, 1:1, 2:2, 3:2, 4:2, 5:4}
//
//    mov &6 0        // {0:1, 1:1, 2:2, 3:2, 4:2, 5:4, 6:0}
//
//    sub &7 &5 &2    // {0:1, 1:1, 2:2, 3:2, 4:2, 5:4, 6:0, 7:2}
//
//    sub &8 &7 &6    // {0:1, 1:1, 2:2, 3:2, 4:2, 5:4, 6:0, 7:2, 8:2}
//
//    println &8      // {0:1, 1:1, 2:2, 3:2, 4:2, 5:4, 6:0, 7:2, 8:2}
    return Arrays.asList(
      new MovInst(0, 1),
      new MovInst(1, 1),
      new AddInst(2, 0, 1),
      new MovInst(3, 2),
      new MovInst(4, 2),
      new AddInst(5, 3, 4),
      new MovInst(6, 0),
      new SubInst(7, 5, 2),
      new SubInst(8, 7, 6),
      new PrintlnInst(8)
    );
  }
}

interface Inst {
  void execute();
}

class MovInst implements Inst {
  public final Integer address;
  public final Integer val;

  MovInst(Integer address, Integer val) {
    this.address = address;
    this.val = val;
  }

  @Override
  public void execute() {
    RegisterDemo.REGISTER.put(address, val);
  }
}

class AddInst implements Inst {
  public final Integer targetAddress;
  public final Integer[] sourceAddresses;

  AddInst(Integer targetAddress, Integer... sourceAddresses) {
    this.targetAddress = targetAddress;
    this.sourceAddresses = sourceAddresses;
  }

  @Override
  public void execute() {
    int sum = 0;
    for (Integer sourceAddress : sourceAddresses) {
      sum += RegisterDemo.REGISTER.get(sourceAddress);
    }
    RegisterDemo.REGISTER.put(targetAddress, sum);
  }
}


class SubInst implements Inst {
  public final Integer targetAddress;
  public final Integer[] sourceAddresses;

  SubInst(Integer targetAddress, Integer... sourceAddresses) {
    this.targetAddress = targetAddress;
    this.sourceAddresses = sourceAddresses;
  }

  @Override
  public void execute() {
    Integer s1 = sourceAddresses[0];
    Integer reduction = RegisterDemo.REGISTER.get(s1);
    for (int i = 1; i < sourceAddresses.length; i++) {
      reduction -= RegisterDemo.REGISTER.get(sourceAddresses[i]);
    }
    RegisterDemo.REGISTER.put(targetAddress, reduction);
  }
}

class PrintlnInst implements Inst {
  public final Integer address;

  PrintlnInst(Integer address) {
    this.address = address;
  }

  @Override
  public void execute() {
    System.out.println(RegisterDemo.REGISTER.get(address));
  }
}
