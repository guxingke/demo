package com.guxingke.demo;

import com.guxingke.demo.biz.proxy.ProxyConfig;
import com.guxingke.demo.biz.test.TestConfig;
import com.guxingke.demo.context.ContextConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
  scanBasePackageClasses = {
    ContextConfig.class,
    TestConfig.class,
    ProxyConfig.class,
  }
)
@Slf4j
public class Application {

  public static void main(String[] args) {
    log.info("starting...");
    SpringApplication.run(Application.class, args);
    log.info("end...");
  }
}