package com.gxk.demo.filter;

import com.gxk.demo.model.HttpRequest;
import com.gxk.demo.model.HttpResponse;

public interface Filter {

  /**
   * if not match, filterChain will exec next filter
   */
  boolean match(HttpRequest request, HttpResponse response);

  void doFilter(HttpRequest request, HttpResponse response, FilterChain chain);
}
