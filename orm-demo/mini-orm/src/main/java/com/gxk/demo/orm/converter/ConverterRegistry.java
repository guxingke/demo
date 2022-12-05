package com.gxk.demo.orm.converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConverterRegistry {

    private Map<Class<?>, BindingConverter<?>> bindingMap;
    private Map<String, ResultSetConverter<?, ?>> resultMap;

    public ConverterRegistry() {
        bindingMap = new HashMap<>();
        resultMap = new HashMap<>();

        init();
    }

    public void init() {
        register(new IntegerBindingConverter());
        register(new IntegerConverter());

        register(new StringBindingConverter());
        register(new StringConverter());

        register(new DateBindingConverter());
        register(new DateResultConverter());
    }

    public synchronized void register(BindingConverter<?> converter) {
        Objects.requireNonNull(converter);

        Class<? extends BindingConverter> bcs = converter.getClass();
        for (Type genericInterface : bcs.getGenericInterfaces()) {
            if (genericInterface instanceof ParameterizedType pt) {
                if (pt.getRawType().getTypeName().equalsIgnoreCase(BindingConverter.class.getCanonicalName())) {
                    bindingMap.put((Class<?>) pt.getActualTypeArguments()[0], converter);
                }
            }
        }

    }

    public synchronized void register(ResultSetConverter<?, ?> converter) {
        Objects.requireNonNull(converter);

        Class<? extends ResultSetConverter> cls = converter.getClass();
        Class<?> javaType = null;
        Class<?> jdbcType = null;
        for (Type genericInterface : cls.getGenericInterfaces()) {
            if (genericInterface instanceof ParameterizedType pt) {
                if (pt.getRawType().getTypeName().equalsIgnoreCase(ResultSetConverter.class.getCanonicalName())) {
                    var actualTypeArguments = pt.getActualTypeArguments();
                    javaType = (Class<?>) actualTypeArguments[0];
                    jdbcType = (Class<?>) actualTypeArguments[1];
                }
            }
        }
        if (jdbcType == null || javaType == null) {
            throw new IllegalStateException();
        }

        resultMap.put(jdbcType.getCanonicalName() + "_" + javaType.getCanonicalName(), converter);
    }

    public BindingConverter<?> binding(Object obj) {
        return bindingMap.get(obj.getClass());
    }

    public ResultSetConverter<?, ?> result(String columnClassName, Class<?> parameterType) {
        return this.resultMap.get(columnClassName + "_" + parameterType.getCanonicalName());
    }
}
