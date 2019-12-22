package com.gxk.demo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class StackDemo {
  public static final Deque<Integer> STACK = new ArrayDeque<>();

  public static void main(String[] args) {
    new StackDemo().run();
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
//    push 1  // (1)
//    push 1  // (1, 1)
//    add     // (2)
//
//    push 2  // (2, 2)
//    push 2  // (2, 2, 2)
//    add     // (2, 4)
//
//    push 0  // (2, 4, 0)
//
//    swap    // (2, 0, 4)
//    swap1   // (0, 2, 4)
//    swap    // (0, 4, 2)
//    sub     // (0, 2)
//
//    swap    // (2, 0)
//    sub     // (2)
//
//    println  // ()
    return Arrays.asList(
      new PushInst(1),
      new PushInst(1),
      new AddInst(),
      new PushInst(2),
      new PushInst(2),
      new AddInst(),
      new PushInst(0),
      new SwapInst(),
      new Swap1Inst(),
      new SwapInst(),
      new SubInst(),
      new SwapInst(),
      new SubInst(),
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
    StackDemo.STACK.push(val);
  }
}

class AddInst implements Inst {

  @Override
  public void execute() {
    Integer v2 = StackDemo.STACK.pop();
    Integer v1 = StackDemo.STACK.pop();
    StackDemo.STACK.push(v1 + v2);
  }
}

class SubInst implements Inst {

  @Override
  public void execute() {
    Integer v2 = StackDemo.STACK.pop();
    Integer v1 = StackDemo.STACK.pop();
    StackDemo.STACK.push(v1 - v2);
  }
}
class SwapInst implements Inst {

  @Override
  public void execute() {
    Integer v2 = StackDemo.STACK.pop();
    Integer v1 = StackDemo.STACK.pop();
    StackDemo.STACK.push(v2);
    StackDemo.STACK.push(v1);
  }
}

class Swap1Inst implements Inst {

  @Override
  public void execute() {
    Integer v3 = StackDemo.STACK.pop();
    Integer v2 = StackDemo.STACK.pop();
    Integer v1 = StackDemo.STACK.pop();
    StackDemo.STACK.push(v2);
    StackDemo.STACK.push(v1);
    StackDemo.STACK.push(v3);
  }
}

class PrintlnInst implements Inst {

  @Override
  public void execute() {
    System.out.println(StackDemo.STACK.pop());
  }
}
