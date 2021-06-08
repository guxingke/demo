package com.gxk.sbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication(
    exclude = {
        ProjectInfoAutoConfiguration.class,
        ApplicationAvailabilityAutoConfiguration.class,
        AopAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        RestTemplateAutoConfiguration.class,
        WebSocketServletAutoConfiguration.class,
        ConfigurationPropertiesAutoConfiguration.class,
        TaskSchedulingAutoConfiguration.class,
        TaskExecutionAutoConfiguration.class,
    }
)
@Lazy
public class SbTest0Application {

  public static void main(String[] args) {
    SpringApplication.run(SbTest0Application.class, args);
  }
}
