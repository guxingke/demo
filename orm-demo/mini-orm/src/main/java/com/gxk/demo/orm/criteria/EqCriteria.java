package com.gxk.demo.orm.criteria;

public class EqCriteria extends BinaryCriteria {
    public final Object v;

    public EqCriteria(BinaryCriteria binaryCriteria, Object v) {
        super(binaryCriteria.name);
        this.v = v;
    }
}
