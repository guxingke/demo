package com.guxingke.demo.biz.proxy.filter.util;

import com.guxingke.demo.biz.proxy.model.HttpRequest;
import com.guxingke.demo.biz.proxy.model.HttpResponse;

import java.util.function.BiFunction;

public class Matchers {

  public static final BiFunction<HttpRequest, HttpResponse, Boolean> BASE_REQUEST_MATCHER = (request, response) -> {
    if (request == null) {
      return false;
    }

    if (response == null) {
      return false;
    }

    return response.getResponse() == null;
  };

  public static final BiFunction<HttpRequest, HttpResponse, Boolean> BASE_RESPONSE_MATCHER = (request, response) -> {
    return response != null && response.getResponse() != null;
  };
}
