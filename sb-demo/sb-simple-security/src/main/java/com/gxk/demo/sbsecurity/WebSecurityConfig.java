package com.gxk.demo.sbsecurity;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebSecurityConfig {

  @Bean
  public FilterRegistrationBean<Filter> securityRegistration(Filter securityFilter) {
    FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(securityFilter);
    bean.setOrder(FilterRegistrationBean.REQUEST_WRAPPER_FILTER_MAX_ORDER - 100);
    return bean;
  }
}