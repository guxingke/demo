package com.gxk.demo.hybrid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridDemo {
  public static final Map<Integer, Integer> REGISTER = new HashMap<>();
  public static final Deque<Integer> STACK = new ArrayDeque<>();

  public static void main(String[] args) {
    new HybridDemo().run();
  }

  public void run() {
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
//    push 1    // (1)     {}
//    push 1    // (1, 1)  {}
//    add       // (2)     {}
//    store &0  // ()      {0:2}
//
//    push 2    // (2)     {0:2}
//    push 2    // (2, 2)  {0:2}
//    add       // (4)     {0:2}
//    store &1  // ()      {0:2, 1:4}
//
//    push 0    // (0)     {0:2, 1:4}
//    store &2  // ()      {0:2, 1:4, 2:0}
//
//    load &1   // (4)     {0:2, 1:4, 2:0}
//    load &0   // (4, 2)  {0:2, 1:4, 2:0}
//    sub       // (2)     {0:2, 1:4, 2:0}
//    store &3  // ()      {0:2, 1:4, 2:0, 3:2}
//
//    load &3   // (2)     {0:2, 1:4, 2:2, 3:2}
//    load &2   // (2, 0)  {0:2, 1:4, 2:2, 3:2}
//    sub       // (2)     {0:2, 1:4, 2:2, 3:2}
//    store $4  // ()      {0:2, 1:4, 2:2, 3:2, 4:2}
//
//    load $4   // (2)     {0:2, 1:4, 2:2, 3:2, 4:2}
//    println   // ()      {0:2, 1:4, 2:2, 3:2, 4:2}

    return Arrays.asList(
      new PushInst(1),
      new PushInst(1),
      new AddInst(),
      new StoreInst(0),

      new PushInst(2),
      new PushInst(2),
      new AddInst(),
      new StoreInst(1),

      new PushInst(0),
      new StoreInst(2),

      new LoadInst(1),
      new LoadInst(0),
      new SubInst(),
      new StoreInst(3),

      new LoadInst(3),
      new LoadInst(2),
      new SubInst(),
      new StoreInst(4),

      new LoadInst(4),
      new PrintlnInst()
    );
  }
}

interface Inst {
  void execute();
}

class PushInst implements Inst {
  public final Integer val;

  PushInst(Integer val) {
    this.val = val;
  }

  @Override
  public void execute() {
    HybridDemo.STACK.push(val);
  }
}

class AddInst implements Inst {

  @Override
  public void execute() {
    Integer v2 = HybridDemo.STACK.pop();
    Integer v1 = HybridDemo.STACK.pop();
    HybridDemo.STACK.push(v1 + v2);
  }
}

class SubInst implements Inst {

  @Override
  public void execute() {
    Integer v2 = HybridDemo.STACK.pop();
    Integer v1 = HybridDemo.STACK.pop();
    HybridDemo.STACK.push(v1 - v2);
  }
}

class StoreInst implements Inst {
  public final Integer address;

  StoreInst(Integer address) {
    this.address = address;
  }

  @Override
  public void execute() {
    Integer val = HybridDemo.STACK.pop();
    HybridDemo.REGISTER.put(address, val);
  }
}

class LoadInst implements Inst {
  public final Integer address;

  LoadInst(Integer address) {
    this.address = address;
  }

  @Override
  public void execute() {
    Integer val = HybridDemo.REGISTER.get(address);
    HybridDemo.STACK.push(val);
  }
}

class PrintlnInst implements Inst {
  @Override
  public void execute() {
    System.out.println(HybridDemo.STACK.pop());
  }
}


