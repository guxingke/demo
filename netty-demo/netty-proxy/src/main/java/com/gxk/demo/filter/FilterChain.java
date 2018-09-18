package com.gxk.demo.filter;

import com.gxk.demo.model.HttpRequest;
import com.gxk.demo.model.HttpResponse;

public interface FilterChain {
  void doFilter(HttpRequest request, HttpResponse response);
}
