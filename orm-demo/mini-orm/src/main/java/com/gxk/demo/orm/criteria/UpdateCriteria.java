package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;

import java.util.ArrayList;
import java.util.List;

public class UpdateCriteria {
  private final Table<?> table;
  private final Generator generator;
  private final SqlExecutor sqlExecutor;

  private List<SetCriteria> criterias;

  private String idc;
  private Object id;

  public UpdateCriteria(Table<?> table, Generator generator, SqlExecutor sqlExecutor) {
    this.generator = generator;
    this.sqlExecutor = sqlExecutor;
    this.table = table;
    criterias = new ArrayList<>();
  }

  public UpdateCriteria id(String column, Object id) {
    this.idc = column;
    this.id = id;
    return this;
  }

  public UpdateCriteria set(String column, Object v) {
    criterias.add(new SetCriteria(column, v));
    return this;
  }

  public int execute() {
    if (criterias.isEmpty()) {
      return 0;
    }
    StringBuilder sql = new StringBuilder();
    sql.append("update ").append(table.tableName()).append(" ");

    List<String> us = new ArrayList<>();
    var args = new Object[criterias.size() + 1];
    int i = 0;
    for (SetCriteria criteria : criterias) {
      us.add(criteria.column + " = ?");
      args[i++] = criteria.v;
    }

    sql.append("SET ").append(String.join(",", us))
        .append("where ").append(idc).append(" = ? ")
        .append(" limit 1");
    args[i] = id;

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.update(realSql, params);
  }
}
