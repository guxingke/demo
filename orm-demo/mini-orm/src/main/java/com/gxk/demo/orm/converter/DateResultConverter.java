package com.gxk.demo.orm.converter;

import java.sql.Timestamp;
import java.util.Date;

public class DateResultConverter implements ResultSetConverter<Date, Timestamp> {

    @Override
    public Object get(Object obj) {
        var v = (Timestamp) obj;
        return new Date(v.getTime());
    }
}
