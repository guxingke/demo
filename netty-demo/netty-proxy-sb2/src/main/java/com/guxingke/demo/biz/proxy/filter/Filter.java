package com.guxingke.demo.biz.proxy.filter;

import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

public interface Filter {

  /**
   * if not match, filterChain will exec next filter
   */
  boolean match(HttpRequest request, HttpResponse response);

  void doFilter(HttpRequest request, HttpResponse response, FilterChain chain);
}
