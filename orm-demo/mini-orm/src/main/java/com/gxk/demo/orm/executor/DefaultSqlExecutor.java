package com.gxk.demo.orm.executor;

import com.gxk.demo.orm.converter.ConverterRegistry;
import com.gxk.demo.orm.table.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DefaultSqlExecutor implements SqlExecutor {
  private final JdbcTemplate jdbcTemplate;
  private final ConverterRegistry converterRegistry;

  public DefaultSqlExecutor(JdbcTemplate jdbcTemplate, ConverterRegistry converterRegistry) {
    this.jdbcTemplate = jdbcTemplate;
    this.converterRegistry = converterRegistry;
  }

  @Override
  public Long count(String sql, Object... params) {
    log.info("count {} {}", sql, params);
    if (params == null || params.length == 0) {
      return this.jdbcTemplate.queryForObject(sql, Long.class);
    }
    var pss = new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
          bind(ps, i + 1, params[i]);
        }
      }
    };
    return this.jdbcTemplate.query(sql, pss, rs -> {
      if (!rs.next()) {
        return 0L;
      }
      return rs.getLong(1);
    });
  }

  @Override
  public boolean exists(String sql, Object[] params) {
    log.info("count {} {}", sql, params);
    if (params == null || params.length == 0) {
      return this.jdbcTemplate.queryForObject(sql, Long.class) != null;
    }
    var pss = new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
          bind(ps, i + 1, params[i]);
        }
      }
    };
    return Boolean.TRUE.equals(this.jdbcTemplate.query(sql, pss, rs -> {
      if (!rs.next()) {
        return null;
      }
      return true;
    }));
  }

  @Override
  public int update(String sql, Object[] params) {
    if (params == null || params.length == 0) {
      return this.jdbcTemplate.update(sql, params);
    }

    var pss = new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
          bind(ps, i + 1, params[i]);
        }
      }
    };
    return this.jdbcTemplate.update(sql, pss);
  }

  @Override
  public long insert(String sql, Object[] params) {
    var keyHolder = new GeneratedKeyHolder();
    var w = jdbcTemplate.update(con -> {
      var ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      for (int i = 0; i < params.length; i++) {
        bind(ps, i + 1, params[i]);
      }
      return ps;
    }, keyHolder);
    if (w == 0 || keyHolder.getKey() == null) {
      return 0L;
    }
    return keyHolder.getKey().longValue();
  }

  public <T> List<T> query(String sql, Object[] params, Table<T> table) {
    log.info("query {} {}", sql, params);
    RowMapper<T> rowMapper = rowMapper(converterRegistry, table);
    if (params == null || params.length == 0) {
      return this.jdbcTemplate.query(sql, rowMapper);
    }

    var pss = new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
          bind(ps, i + 1, params[i]);
        }
      }
    };
    return this.jdbcTemplate.query(sql, pss, rowMapper);
  }

  private static <T> RowMapper<T> rowMapper(ConverterRegistry converterRegistry, Table<T> table) {
    RowMapper<T> rowMapper = (rs, rowNum) -> {
      T obj = table.newObject();
      var md = rs.getMetaData();
      for (int i = 1; i < md.getColumnCount() + 1; i++) {
        var columnName = md.getColumnName(i);
        var columnClassName = md.getColumnClassName(i);
        Method setter = table.setter(columnName);
        if (setter == null) {
          log.debug("skip column because setter method not found {}", columnName);
          continue;
        }

        var value = rs.getObject(columnName);
        if (value == null) {
          // skip
          continue;
        }

        var converter = converterRegistry.result(columnClassName, setter.getParameterTypes()[0]);
        var newVal = converter.get(value);

        table.set(obj, newVal, setter);
      }
      return obj;
    };
    return rowMapper;
  }

  private void bind(PreparedStatement ps, int idx, Object v) throws SQLException {
    Objects.requireNonNull(v);
    var binding = converterRegistry.binding(v);
    if (binding == null) {
      throw new IllegalStateException();
    }
    binding.binding(ps, idx, v);
  }

}
