package com.gxk.demo.orm.converter;

/**
 * 1. result set -> object
 * jdbc type + object type
 * -> object type
 */
public interface ResultSetConverter<JavaType, JdbcType> {

    Object get(Object obj);
}
