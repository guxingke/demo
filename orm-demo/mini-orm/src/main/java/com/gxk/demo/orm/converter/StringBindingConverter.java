package com.gxk.demo.orm.converter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class StringBindingConverter implements BindingConverter<String> {
    @Override
    public void binding(PreparedStatement ps, int idx, Object vv) throws SQLException {
        Objects.requireNonNull(vv);
        ps.setString(idx, ((String) vv));
    }
}
