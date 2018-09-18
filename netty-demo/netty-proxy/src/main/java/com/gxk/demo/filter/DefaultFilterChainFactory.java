package com.gxk.demo.filter;

import com.gxk.demo.filter.impl.DefaultFilterChain;

public class DefaultFilterChainFactory {

  public static FilterChain newFilterChain() {
    return new DefaultFilterChain(FilterRegistry.getInstance().getFilters());
  }
}
