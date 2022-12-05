package com.gxk.demo.orm.criteria;

public class NotNullCriteria extends BinaryCriteria {
    public NotNullCriteria(BinaryCriteria binaryCriteria) {
        super(binaryCriteria.name);
    }
}
