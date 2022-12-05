package com.gxk.demo.orm;

import com.gxk.demo.orm.converter.ConverterRegistry;
import com.gxk.demo.orm.criteria.*;
import com.gxk.demo.orm.executor.DefaultSqlExecutor;
import com.gxk.demo.orm.executor.SqlExecutor;
import com.gxk.demo.orm.table.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DatabaseTemplate {
  private final Generator generator;
  private final SqlExecutor sqlExecutor;
  private final ConverterRegistry converterRegistry;

  public DatabaseTemplate(Generator generator, JdbcTemplate jdbcTemplate) {
    this.generator = generator;
    this.converterRegistry = new ConverterRegistry();
    sqlExecutor = new DefaultSqlExecutor(jdbcTemplate, this.converterRegistry);
  }

  public <T> SelectCriteria<T> from(Class<T> entity) {
    return new SelectCriteria<>(entity, new Table<>(entity), generator, sqlExecutor);
  }

  public <T> Optional<T> findById(Object id, Class<T> entity) {
    Table<?> table = new Table<>(entity);
    Field field = table.fetchIdField(entity);
    return from(entity).where(Criteria.column(field.getName()).is(id)).first();
  }

  // save
  public <T> boolean save(T entity) {
    // exists
    Class<?> cls = entity.getClass();
    Table<?> table = new Table<>(cls);
    var id = table.fetchId(entity);
    var field = table.fetchIdField(entity);
    if (id == null) {
      // insert
      var ic = new InsertCriteria(table, generator, sqlExecutor);
      Map<String, Object> vv = table.columns(entity);
      vv.forEach(ic::set);
      var v = ic.executeAndGetGeneratedKey();

      try {
        field.setAccessible(true);
        if (field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(int.class)) {
          field.set(entity, ((int) v));
        } else if (field.getType().isAssignableFrom(Long.class) || field.getType().isAssignableFrom(long.class)) {
          field.set(entity, v);
        } else {
          throw new IllegalStateException();
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      return v != 0L;
    }
    // fetch
    boolean exists = from(cls).where(Criteria.column(field.getName()).is(id)).exists();
    if (!exists) {
      // insert
      var ic = new InsertCriteria(table, generator, sqlExecutor);
      Map<String, Object> vv = table.columns(entity);
      vv.forEach(ic::set);
      return ic.execute() == 1;
    }

    // update
    // update table set x = ?, y = ? where id = ? limit 1;
    var up = new UpdateCriteria(table, generator, sqlExecutor);
    up.id(field.getName(), id);

    Map<String, Object> vv = table.columns(entity);
    vv.forEach(up::set);
    return up.execute() == 1;
  }

  public RawQueryCriteria createQuery(String sql, Object[] objects) {
    return new RawQueryCriteria(sql, objects, generator, sqlExecutor);
  }
}
