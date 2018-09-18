package com.guxingke.demo.biz.proxy.filter;


import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

public interface FilterChain {
  void doFilter(HttpRequest request, HttpResponse response);
}
