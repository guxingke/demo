package com.gxk.demo.orm.table;

import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Table<T> {
  private final Class<T> entityClass;

  public Table(Class<T> entityClass) {
    Objects.requireNonNull(entityClass);
    this.entityClass = entityClass;
    if (!idPresent()) {
      throw new IllegalStateException();
    }
  }

  public String tableName() {
    var annotation = entityClass.getAnnotation(jakarta.persistence.Table.class);
    String name = null;
    if (annotation != null) {
      name = annotation.name();
    }
    if (name == null || name.isBlank()) {
      name = entityClass.getName();
    }
    return name;
  }

  public T newObject() {
    try {
      return entityClass.newInstance();
    } catch (Exception e) {
      log.error("failed instance for {}", entityClass);
      throw new RuntimeException(e);
    }
  }

  public Method setter(String columnName) {
    for (Method m : entityClass.getDeclaredMethods()) {
      if (m.getName().startsWith("set")) {
        if (m.getName().substring(3).equalsIgnoreCase(columnName)) {
          if (m.getParameterCount() == 1) {
            return m;
          }
        }
      }
    }
    return null;
  }

  public void set(T obj, Object value, Method setter) {
    try {
      setter.invoke(obj, value);
    } catch (Exception e) {
      log.error("set property failed {} {} {} {}", entityClass, obj, value, setter, e);
      throw new RuntimeException(e);
    }
  }

  public boolean idPresent() {
    try {
      var idField = entityClass.getDeclaredField("id");
      var annotation = idField.getAnnotation(Id.class);
      if (annotation != null) {
        return true;
      }
      for (Field declaredField : entityClass.getDeclaredFields()) {
        if (declaredField.getAnnotation(Id.class) != null) {
          return true;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  private Field idField() {
    try {
      var idField = entityClass.getDeclaredField("id");
      var annotation = idField.getAnnotation(Id.class);
      if (annotation != null) {
        return idField;
      }
      for (Field declaredField : entityClass.getDeclaredFields()) {
        if (declaredField.getAnnotation(Id.class) != null) {
          return idField;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public Field fetchIdField(Object entity) {
    var field = idField();
    Objects.requireNonNull(field);
    return field;
  }

  public Object fetchId(Object entity) {
    var field = idField();
    Objects.requireNonNull(field);
    try {
      field.setAccessible(true);
      return field.get(entity);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  public <T> Map<String, Object> columns(T entity) {
    var map = new LinkedHashMap<String, Object>();
    for (Field f : entityClass.getDeclaredFields()) {
      f.setAccessible(true);
      try {
        var v = f.get(entity);
        if (v != null) {
          map.put(f.getName(), v);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return map;
  }
}
