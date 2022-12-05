package com.gxk.demo.orm.converter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class IntegerBindingConverter implements BindingConverter<Integer> {
    @Override
    public void binding(PreparedStatement ps, int idx, Object vv) throws SQLException {
        Objects.requireNonNull(vv);
        ps.setInt(idx, ((Integer) vv));
    }
}
