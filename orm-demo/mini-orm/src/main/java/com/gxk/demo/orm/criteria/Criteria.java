package com.gxk.demo.orm.criteria;

public interface Criteria {

    static BinaryCriteria column(String column) {
        return new BinaryCriteria(column);
    }

    static Criteria and(Criteria... criteria) {
        return new AndCriteria(criteria);
    }

    static Criteria or(Criteria... criteria) {
        return new OrCriteria(criteria);
    }
}
