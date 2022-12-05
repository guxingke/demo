package com.gxk.demo.orm.criteria;

public class RangeCriteria extends BinaryCriteria {
    public final Object left;
    public final Object right;

    public RangeCriteria(BinaryCriteria binaryCriteria, Object left, Object right) {
        super(binaryCriteria.name);
        this.left = left;
        this.right = right;
    }
}
