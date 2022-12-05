package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.Pair;
import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;

import java.util.List;
import java.util.Optional;

public class SelectCriteria<T> {
  private final Table<T> table;
  private final Generator generator;

  private final SqlExecutor sqlExecutor;

  private String columns = "*";

  private String condition;
  private Object[] args;

  private long offset = 0;
  private long limit = 1;

  public SelectCriteria(Class<T> cls, Table<T> table, Generator generator, SqlExecutor sqlExecutor) {
    this.generator = generator;
    this.sqlExecutor = sqlExecutor;
    this.table = table;
  }

  public SelectCriteria<T> projects(String... columns) {
    this.columns = String.join(", ", columns);
    return this;
  }

  public SelectCriteria<T> where(String condition, Object... args) {
    this.condition = condition;
    this.args = args;
    return this;
  }

  public SelectCriteria<T> where(Criteria criteria) {
    Pair<String, Object[]> p = generator.gen(criteria);
    this.condition = p.left();
    this.args = p.right();
    return this;
  }

  public SelectCriteria<T> skip(long offset) {
    this.offset = offset;
    return this;
  }

  public SelectCriteria<T> limit(long limit) {
    this.limit = limit;
    return this;
  }

  // terminal
  public List<T> list() {
    StringBuilder sql = new StringBuilder();
    sql.append("select ")
        .append(columns)
        .append(" from ")
        .append(table.tableName())
        .append(" ");
    if (condition != null) {
      sql.append("where ").append(condition);
    }
    sql.append(" limit ")
        .append(offset)
        .append(" , ")
        .append(limit);
    sql.append(" ;");

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.query(realSql, params, table);
  }

  public long count() {
    StringBuilder sql = new StringBuilder();
    sql.append("select count(*) from ")
        .append(table.tableName()).append(" ");
    if (condition != null) {
      sql.append("where ").append(condition);
    }
    sql.append(" ;");

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.count(realSql, params);
  }

  public Optional<T> first() {
    this.limit = 1;
    var list = this.list();
    if (list.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(list.get(0));
  }

  public boolean exists() {
    StringBuilder sql = new StringBuilder();
    sql.append("select 1 from ")
        .append(table.tableName()).append(" ");
    if (condition != null) {
      sql.append("where ").append(condition);
    }
    sql.append(" ;");

    var realSql = sql.toString();
    Object[] params = args;
    return sqlExecutor.exists(realSql, params);
  }
  // end terminal
}
