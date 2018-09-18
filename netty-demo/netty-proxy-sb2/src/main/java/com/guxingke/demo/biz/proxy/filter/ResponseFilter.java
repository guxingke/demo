package com.guxingke.demo.biz.proxy.filter;

import com.guxingke.demo.biz.proxy.filter.util.Matchers;
import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

import java.util.function.BiConsumer;

public class ResponseFilter extends BaseFilter {
  public ResponseFilter(BiConsumer<HttpRequest, HttpResponse> filter) {
    super(Matchers.BASE_RESPONSE_MATCHER, filter);
  }
}
