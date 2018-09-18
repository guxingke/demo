package com.guxingke.demo.biz.proxy.filter;

import com.guxingke.demo.biz.proxy.filter.util.Matchers;
import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

import java.util.function.BiConsumer;

public class RequestFilter extends BaseFilter {

  public RequestFilter(BiConsumer<HttpRequest, HttpResponse> filter) {
    super(Matchers.BASE_REQUEST_MATCHER, filter);
  }
}
