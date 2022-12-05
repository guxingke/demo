package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;

import java.util.List;
import java.util.Optional;

public class QueryCriteria<T> {
  private final Generator generator;

  private final SqlExecutor sqlExecutor;

  private final String sql;
  private final Object[] args;

  private final Class<T> cls;
  private final Table<T> table;

  public QueryCriteria(String sql, Object[] args, Generator generator, SqlExecutor sqlExecutor, Class<T> cls, Table<T> table) {
    this.generator = generator;
    this.sqlExecutor = sqlExecutor;
    this.args = args;
    this.sql = sql;
    this.cls = cls;
    this.table = table;
  }

  public List<T> list() {
    return sqlExecutor.query(sql, args, table);
  }

  public Optional<T> first() {
    var list = this.list();
    if (list.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(list.get(0));
  }

}
