package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratorImpl implements Generator {
  @Override
  public Pair<String, Object[]> gen(Criteria criteria) {
    if (criteria instanceof AndCriteria and) {
      return genAnd(and);
    }
    if (criteria instanceof OrCriteria or) {
      return genOr(or);
    }
    if (criteria instanceof BinaryCriteria bin) {
      return genBinary(bin);
    }

    return Pair.of("name = ?", new Object[]{"test"});
  }

  private Pair<String, Object[]> genBinary(BinaryCriteria bin) {
    if (bin instanceof EqCriteria eq) {
      return Pair.of(eq.name + " = ?", new Object[]{eq.v});
    }
    if (bin instanceof NullCriteria nil) {
      return Pair.of(nil.name + " IS NULL", new Object[0]);
    }
    if (bin instanceof NotNullCriteria notnull) {
      return Pair.of(notnull.name + " IS NOT NULL", new Object[0]);
    }
    if (bin instanceof NotEqCriteria ne) {
      return Pair.of(ne.name + " != ?", new Object[]{ne.v});
    }
    if (bin instanceof RangeCriteria range) {
      return Pair.of(range.name + " BETWEEN ? AND ?", new Object[]{
          range.left,
          range.right
      });
    }
    throw new IllegalStateException();
  }

  private Pair<String, Object[]> genOr(OrCriteria or) {
    var children = or.criteria;
    if (children.length == 0) {
      throw new IllegalStateException();
    }
    List<String> sts = new ArrayList<>();
    List<Object> objects = new ArrayList<>();
    Arrays.stream(children).map(this::gen)
        .forEach(it -> {
          sts.add(it.left());
          for (Object obj : it.right()) {
            objects.add(obj);
          }
        });

    return Pair.of("(" + String.join(" OR ", sts) + ")", objects.toArray(new Object[0]));
  }

  private Pair<String, Object[]> genAnd(AndCriteria and) {
    var children = and.criteria;
    if (children.length == 0) {
      throw new IllegalStateException();
    }
    List<String> sts = new ArrayList<>();
    List<Object> objects = new ArrayList<>();
    Arrays.stream(children).map(this::gen)
        .forEach(it -> {
          sts.add(it.left());
          for (Object obj : it.right()) {
            objects.add(obj);
          }
        });

    return Pair.of("(" + String.join(" AND ", sts) + ")", objects.toArray(new Object[0]));
  }
}
