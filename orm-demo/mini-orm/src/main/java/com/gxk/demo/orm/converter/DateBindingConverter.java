package com.gxk.demo.orm.converter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class DateBindingConverter implements BindingConverter<Date> {
    @Override
    public void binding(PreparedStatement ps, int idx, Object v) throws SQLException {
        var v2 = (Date) v;
        ps.setTimestamp(idx, new Timestamp(v2.getTime()));
    }
}
