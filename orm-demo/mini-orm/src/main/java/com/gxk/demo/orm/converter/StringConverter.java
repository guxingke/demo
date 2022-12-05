package com.gxk.demo.orm.converter;

public class StringConverter implements ResultSetConverter<String, String> {

    @Override
    public Object get(Object obj) {
        return obj;
    }
}
