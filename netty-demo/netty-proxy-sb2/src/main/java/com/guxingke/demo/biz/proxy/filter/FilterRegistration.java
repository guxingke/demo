package com.guxingke.demo.biz.proxy.filter;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilterRegistration {
  private Filter filter;
  private Integer order = Integer.MAX_VALUE;
  private String path;
}
