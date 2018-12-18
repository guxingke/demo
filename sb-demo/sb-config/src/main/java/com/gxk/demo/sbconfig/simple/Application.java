package com.gxk.demo.sbconfig.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Value("${cmd.config}")
  private String name;

  @Override
  public void run(String... args) {
    log.info("log info from application.properties");
    log.error(name);
    log.debug(name);
  }
}
