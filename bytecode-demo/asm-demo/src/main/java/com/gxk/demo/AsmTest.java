package com.gxk.demo;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmTest implements Opcodes {

  public byte[] test() {
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
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
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
      mv.visitCode();
//      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//      mv.visitLdcInsn("test");
      mv.visitMethodInsn(INVOKESTATIC, "Test", "test", "()V", false);
      mv.visitMethodInsn(INVOKESTATIC, "Test", "test", "()Z", false);
//      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "test", "()V", null, null);
      mv.visitCode();
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("tv");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "test", "()Z", null, null);
      mv.visitCode();
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("tz");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitMaxs(0, 0);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
