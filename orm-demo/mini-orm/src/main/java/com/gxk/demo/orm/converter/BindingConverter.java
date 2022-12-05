package com.gxk.demo.orm.converter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 1. prepare statement 赋值
 * object + ?
 */
public interface BindingConverter<JavaType> {

    void binding(PreparedStatement ps, int idx, Object v) throws SQLException;
}
