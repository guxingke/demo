package com.gxk.demo.jvm;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JvmDemo {

  public static void main(String[] args) throws IOException {
    new JvmDemo().run(args[0]);
  }

  public void run(String path) throws IOException {
    List<Inst> insts = parseInst(path + ".class");
    // 由于 jvm 指令有步长的概念, 此处需要转为map.
    Map<Integer, Inst> instructions = genInstructions(insts);

    // 10 是临时写死, 实际应从 class 文件中解析得到.
    Frame frame = new Frame(10, 10);
    while (true) {
      int pc = frame.pc;
      Inst inst = instructions.get(pc);
      if (inst == null) {
        break;
      }
      inst.execute(frame);
      if (pc == frame.pc) {
        frame.pc += inst.offset();
      }
    }
  }

  public static List<Inst> parseInst(String path) throws IOException {
    try (InputStream inputStream = Files.newInputStream(Paths.get(path));
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        DataInputStream is = new DataInputStream(bis)) {
      {
        int magic = is.readInt();
        int minorVersion = is.readUnsignedShort();
        int majorVersion = is.readUnsignedShort();

        int cpSize = is.readUnsignedShort();
        for (int i = 0; i < cpSize - 1; i++) {
          int tag = is.readUnsignedByte();
          if (tag == 1) {
            int size = is.readUnsignedShort();
            for (int i1 = 0; i1 < size; i1++) {
              is.readUnsignedByte();
            }
            continue;
          }
          if (tag == 10 || tag == 9 || tag == 12) {
            for (int i1 = 0; i1 < 4; i1++) {
              is.readUnsignedByte();
            }
            continue;
          }
          if (tag == 7) {
            for (int i1 = 0; i1 < 2; i1++) {
              is.readUnsignedByte();
            }
            continue;
          }

          throw new IllegalStateException();
        }

        int accessFlag = is.readUnsignedShort();
        int thisClass = is.readUnsignedShort();
        int superClass = is.readUnsignedShort();

        // read interfaces, ignore
        is.readUnsignedShort();
        // read field, ignore
        is.readUnsignedShort();

        is.readUnsignedShort();

        // read <init>, ignore
        is.readUnsignedShort();
        is.readUnsignedShort();
        is.readUnsignedShort();
        is.readUnsignedShort();
        is.readUnsignedShort();
        int l1 = is.readInt();
        for (int i = 0; i < l1; i++) {
          is.readUnsignedByte();
        }
      }
      // core, read main method
      is.readUnsignedShort();
      is.readUnsignedShort();
      is.readUnsignedShort();
      is.readUnsignedShort();
      is.readUnsignedShort();
      is.readInt();
      is.readUnsignedShort();
      is.readUnsignedShort();
      int codeLen = is.readInt();

      int len = codeLen;
      List<Inst> insts = new ArrayList<>();
      Inst inst = null;
      while (len > 0) {
        int code = is.readUnsignedByte();
        switch (code) {
          case 0x03:
            inst = new IConst0();
            break;
          case 0x04:
            inst = new IConst1();
            break;
          case 0x3c:
            inst = new IStore1();
            break;
          case 0x3d:
            inst = new IStore2();
            break;
          case 0x10:
            inst = new Bipush(is.readByte());
            break;
          case 0xa3:
            inst = new IfIcmpGt(is.readShort());
            break;
          case 0x60:
            inst = new Iadd();
            break;
          case 0x84:
            inst = new Iinc(is.readUnsignedByte(), is.readByte());
            break;
          case 0xa7:
            inst = new Goto(is.readShort());
            break;
          case 0x1b:
            inst = new ILoad1();
            break;
          case 0x1c:
            inst = new ILoad2();
            break;
          case 0xb1:
            inst = new Return();
            break;
          case 0xb2:
            is.readUnsignedShort();
            inst = new Getstatic();
            break;
          case 0xb6:
            is.readUnsignedShort();
            inst = new Invokevirtual();
            break;
          default:
            throw new UnsupportedOperationException();
        }
        len -= inst.offset();
        insts.add(inst);
      }

      return insts;
    }
  }

  private static Map<Integer, Inst> genInstructions(List<Inst> insts) {
    Map<Integer, Inst> map = new LinkedHashMap<>(insts.size());
    int i = 0;
    for (Inst inst : insts) {
      map.put(i, inst);
      i += inst.offset();
    }
    return map;
  }
}

interface Inst {

  default int offset() {
    return 1;
  }

  void execute(Frame frame);
}

class IStore1 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.localVars[1] = frame.operandStack.pop();
  }
}

class IStore2 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.localVars[2] = frame.operandStack.pop();
  }
}

class ILoad1 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(frame.localVars[1]);
  }
}

class ILoad2 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(frame.localVars[2]);
  }
}

class Bipush implements Inst {

  final int val;

  Bipush(int val) {
    this.val = val;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(val);
  }
}

class IfIcmpGt implements Inst {

  final int offset;

  IfIcmpGt(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val2 = (Integer) frame.operandStack.pop();
    Integer val1 = (Integer) frame.operandStack.pop();
    if (val1 > val2) {
      frame.pc += offset;
    }
  }
}

class Iadd implements Inst {

  @Override
  public void execute(Frame frame) {
    Integer val2 = (Integer) frame.operandStack.pop();
    Integer val1 = (Integer) frame.operandStack.pop();
    frame.operandStack.push(val1 + val2);
  }
}

class Iinc implements Inst {

  final int index;
  final int val;

  Iinc(int index, int val) {
    this.index = index;
    this.val = val;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = ((Integer) frame.localVars[index]);
    tmp += val;
    frame.localVars[index] = tmp;
  }
}

class IConst0 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(0);
  }
}

class IConst1 implements Inst {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(1);
  }
}

class Goto implements Inst {
  final int offset;

  Goto(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.pc += offset;
  }
}

class Return implements Inst {

  @Override
  public void execute(Frame frame) {
    // do nothings
  }
}

class Getstatic implements Inst {

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.operandStack.push(null);
  }
}

class Invokevirtual implements Inst {

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Object val = frame.operandStack.pop();
    Object thisObj = frame.operandStack.pop();
    System.out.println(val);
  }
}

class Frame {

  public final Object[] localVars;
  public final Stack<Object> operandStack;
  public int pc = 0;

  public Frame(int locals, int stacks) {
    this.localVars = new Object[locals];
    this.operandStack = new Stack<>();
  }
}
