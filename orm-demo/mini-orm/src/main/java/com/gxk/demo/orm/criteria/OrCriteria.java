package com.gxk.demo.orm.criteria;

public class OrCriteria implements Criteria{
    public final Criteria[] criteria;

    public OrCriteria(Criteria[] criteria) {
        this.criteria = criteria;
    }
}
