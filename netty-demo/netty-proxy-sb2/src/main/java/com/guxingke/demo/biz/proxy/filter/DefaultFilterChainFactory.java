package com.guxingke.demo.biz.proxy.filter;


import com.guxingke.demo.biz.proxy.filter.impl.DefaultFilterChain;

public class DefaultFilterChainFactory {

  public static FilterChain newFilterChain() {
    return new DefaultFilterChain(FilterRegistry.getInstance().getFilters());
  }
}
