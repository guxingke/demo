package com.guxingke.demo.biz.proxy.filter;

import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BaseFilter implements Filter {

  private final BiFunction<HttpRequest, HttpResponse, Boolean> match;
  private final BiConsumer<HttpRequest, HttpResponse> func;

  public BaseFilter(BiFunction<HttpRequest, HttpResponse, Boolean> match, BiConsumer<HttpRequest, HttpResponse> filter) {
    this.match = match;
    this.func = filter;
  }

  @Override
  public boolean match(HttpRequest request, HttpResponse response) {
    return match.apply(request, response);
  }

  @Override
  public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) {
    func.accept(request, response);
    chain.doFilter(request, response);
  }
}
