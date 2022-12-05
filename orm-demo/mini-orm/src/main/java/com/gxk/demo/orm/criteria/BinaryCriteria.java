package com.gxk.demo.orm.criteria;

/**
 * 二元
 */
public class BinaryCriteria implements Criteria {
    protected final String name;

    public BinaryCriteria(String name) {
        this.name = name;
    }

    public EqCriteria is(Object v) {
        return new EqCriteria(this, v);
    }

    public NotEqCriteria notIs(Object v) {
        return new NotEqCriteria(this, v);
    }

    public NullCriteria isNull() {
        return new NullCriteria(this);
    }

    public NotNullCriteria notNull() {
        return new NotNullCriteria(this);
    }

    public <T> RangeCriteria range(T left, T right) {
        return new RangeCriteria(this, left, right);
    }
}
