package com.gxk.demo.orm.executor;

import com.gxk.demo.orm.table.Table;

import java.util.List;

public interface SqlExecutor {

  <T> List<T> query(String sql, Object[] params, Table<T> table);

  Long count(String sql, Object[] params);

  boolean exists(String sql, Object[] params);

  int update(String sql, Object[] params);

  long insert(String sql, Object[] params);
}
