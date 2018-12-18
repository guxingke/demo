package com.gxk.demo.sbconfig.cmd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CmdConfigApplication implements CommandLineRunner {

  public static void main(String[] args) {
    args = new String[1];
    args[0] = "--spring.config.location=classpath:/cmd.properties";
    SpringApplication.run(CmdConfigApplication.class, args);
  }

  @Value("${cmd.config}")
  private String name;

  @Override
  public void run(String... args) {
    log.info("log info from cmd");
    log.error(name);
    log.debug(name);
  }
}
