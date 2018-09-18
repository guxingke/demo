package com.guxingke.demo.biz.proxy.filter.impl;


import com.guxingke.demo.biz.proxy.filter.Filter;
import com.guxingke.demo.biz.proxy.filter.FilterChain;
import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

import java.util.List;

// always new it
public class DefaultFilterChain implements FilterChain {
  private final List<Filter> filters;
  private int idx = 0;

  public DefaultFilterChain(List<Filter> filters) {
    this.filters = filters;
  }

  @Override
  public void doFilter(HttpRequest request, HttpResponse response) {

    if (idx < filters.size()) {

      System.out.println("filterChain idx: " + idx);
      Filter filter = filters.get(idx++);

      if (!filter.match(request, response)) {
        this.doFilter(request, response);
        return;
      }

      filter.doFilter(request, response, this);

      return;
    }
  }
}
