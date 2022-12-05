package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;

public class RawQueryCriteria {
  private final Generator generator;

  private final SqlExecutor sqlExecutor;

  private final String sql;
  private final Object[] args;

  public RawQueryCriteria(String sql, Object[] args, Generator generator, SqlExecutor sqlExecutor) {
    this.generator = generator;
    this.sqlExecutor = sqlExecutor;
    this.args = args;
    this.sql = sql;
  }

  public <T> QueryCriteria<T> map(
      Class<T> cls) {
    return new QueryCriteria<>(sql, args, generator, sqlExecutor, cls, new Table<>(cls));
  }
}
