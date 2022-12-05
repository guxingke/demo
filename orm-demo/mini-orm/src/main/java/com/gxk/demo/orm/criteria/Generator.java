package com.gxk.demo.orm.criteria;

import com.gxk.demo.orm.Pair;

public interface Generator {

    Pair<String, Object[]> gen(Criteria criteria);
}
