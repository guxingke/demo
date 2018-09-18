package com.gxk.demo.filter;

import com.gxk.demo.model.HttpRequest;
import com.gxk.demo.model.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class FilterRegistry {
  private static FilterRegistry me = new FilterRegistry();

  private FilterRegistry() {
  }

  public static FilterRegistry getInstance() {
    return me;
  }

  private List<Filter> filters = new ArrayList<>();

  public List<Filter> getFilters() {
    return new ArrayList<>(filters);
  }

  public FilterRegistry registerFilter(Filter filter) {
    this.filters.add(filter);
    return this;
  }

  public FilterRegistry registerRequestFilter(BiConsumer<HttpRequest, HttpResponse> filter) {
    BiFunction<HttpRequest, HttpResponse, Boolean> match = (request, response) -> {
      if (request == null) {
        return false;
      }

      if (response == null) {
        return false;
      }

      if (response.getResponse() != null) {
        return false;
      }

      return true;
    };

    return this.registerFilter(match, filter);
  }

  public FilterRegistry registerResponseFilter(BiConsumer<HttpRequest, HttpResponse> filter) {
    BiFunction<HttpRequest, HttpResponse, Boolean> match = (request, response) -> {
      if (response == null || response.getResponse() == null) {
        return false;
      }

      return true;
    };

    return this.registerFilter(match, filter);
  }

  public FilterRegistry registerFilter(BiFunction<HttpRequest, HttpResponse, Boolean> match, BiConsumer<HttpRequest, HttpResponse> filter) {
    this.filters.add(new FnFilter(match, filter));
    return this;
  }
}
