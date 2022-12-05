package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;

import java.util.ArrayList;
import java.util.List;

public class InsertCriteria {
  private final Table<?> table;
  private final Generator generator;
  private final SqlExecutor sqlExecutor;

  private List<SetCriteria> criterias;

  public InsertCriteria(Table<?> table, Generator generator, SqlExecutor sqlExecutor) {
    this.generator = generator;
    this.sqlExecutor = sqlExecutor;
    this.table = table;
    criterias = new ArrayList<>();
  }

  public InsertCriteria set(String column, Object v) {
    criterias.add(new SetCriteria(column, v));
    return this;
  }

  public long executeAndGetGeneratedKey() {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO ").append(table.tableName()).append(" ");

    List<String> us = new ArrayList<>();
    List<String> qs = new ArrayList<>();
    var args = new Object[criterias.size()];
    int i = 0;
    for (SetCriteria criteria : criterias) {
      us.add(criteria.column);
      qs.add("?");
      args[i++] = criteria.v;
    }

    sql.append("(")
        .append(String.join(",", us))
        .append(")")
        .append(" values ")
        .append("(")
        .append(String.join(",", qs))
        .append(")");

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.insert(realSql, params);
  }

  public int execute() {
    if (criterias.isEmpty()) {
      return 0;
    }
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO ").append(table.tableName()).append(" ");

    List<String> us = new ArrayList<>();
    List<String> qs = new ArrayList<>();
    var args = new Object[criterias.size()];
    int i = 0;
    for (SetCriteria criteria : criterias) {
      us.add(criteria.column);
      qs.add("?");
      args[i++] = criteria.v;
    }

    sql.append("(")
        .append(String.join(",", us))
        .append(")")
        .append(" values ")
        .append("(")
        .append(String.join(",", qs))
        .append(")");

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.update(realSql, params);
  }
}
