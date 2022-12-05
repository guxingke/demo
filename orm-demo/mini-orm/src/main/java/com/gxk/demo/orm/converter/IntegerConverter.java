package com.gxk.demo.orm.converter;

public class IntegerConverter implements ResultSetConverter<Integer, Integer> {


    @Override
    public Object get(Object obj) {
        return obj;
    }
}
