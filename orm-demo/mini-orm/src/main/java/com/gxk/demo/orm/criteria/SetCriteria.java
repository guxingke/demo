package com.gxk.demo.orm.criteria;

public class SetCriteria implements Criteria {

  public final String column;
  public final Object v;

  public SetCriteria(String column, Object v) {
    this.column = column;
    this.v = v;
  }
}
