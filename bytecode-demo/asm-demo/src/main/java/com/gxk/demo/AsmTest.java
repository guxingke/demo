package com.gxk.demo;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmTest implements Opcodes {

  public byte[] test() {
    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;
    AnnotationVisitor av0;

    cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);

//    cw.visitSource("Test.java", null);

    {
      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
//      Label l0 = new Label();
//      mv.visitLabel(l0);
//      mv.visitLineNumber(1, l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
      mv.visitInsn(RETURN);
//      Label l1 = new Label();
//      mv.visitLabel(l1);
//      mv.visitLocalVariable("this", "LTest;", null, l0, l1, 0);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
      mv.visitCode();
//      Label l0 = new Label();
//      mv.visitLabel(l0);
//      mv.visitLineNumber(3, l0);
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("test");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//      Label l1 = new Label();
//      mv.visitLabel(l1);
//      mv.visitLineNumber(4, l1);
      mv.visitInsn(RETURN);
//      Label l2 = new Label();
//      mv.visitLabel(l2);
//      mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l0, l2, 0);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
