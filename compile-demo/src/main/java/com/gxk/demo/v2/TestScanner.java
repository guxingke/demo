package com.gxk.demo.v2;

import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.util.TreePathScanner;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;
import java.util.HashSet;
import java.util.Set;

public class TestScanner extends TreePathScanner<Void, Void> {

  private Set<String> acs = new HashSet<>();

  {
    acs.add("java.lang.Math");
    acs.add("java.util.List");
    acs.add("java.util.ArrayList");
    acs.add("java.util.Set");
    acs.add("java.util.HashSet");
    acs.add("java.util.Map");
    acs.add("java.util.HashMap");

    acs.add("com.gxk.demo.v1.Test");
  }

  @Override
  public Void visitMethodInvocation(MethodInvocationTree node, Void trees) {
    final JCMethodInvocation mn = (JCMethodInvocation) node;
    final JCExpression meth = mn.meth;

    if (meth instanceof JCIdent) {
      return super.visitMethodInvocation(node, trees);
    }

    final JCFieldAccess jfa = (JCFieldAccess) meth;
    final String cn = jfa.selected.toString();
    final String n = jfa.name.toString();
    // check allow list
    if (!acs.contains(cn)) {
      throw new IllegalStateException("not allow method invocation " + cn + "." + n);
    }

    return super.visitMethodInvocation(node, trees);
  }

  @Override
  public Void visitMethod(MethodTree node, Void trees) {
    return super.visitMethod(node, trees);
  }

}