package com.gxk.demo.sbconfig.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class DtConfigRunListener implements SpringApplicationRunListener, Ordered {
  private final SpringApplication application;

  private final String[] args;

  private final PropertiesPropertySourceLoader loader;

  public DtConfigRunListener(SpringApplication application, String[] args) {
    this.application = application;
    this.args = args;
    loader = new PropertiesPropertySourceLoader();
  }

  @Override
  public void starting() {

    ConfigurableEnvironment environment = this.createEnvironment();

    Class<?> applicationClass = application.getMainApplicationClass();
    DtConfig annotation = applicationClass.getAnnotation(DtConfig.class);
    if (annotation == null) {
      return;
    }

    String path = annotation.value();

    Resource resource = null;
    if (path.startsWith("classpath")) {
      resource = new ClassPathResource(path.substring(10));
    } else {
      try {
        resource = new FileUrlResource(path);
      } catch (MalformedURLException e) {
        resource = new FileSystemResource(path);
      }
    }

    PropertySource<?> propertySource = loadProperties("Dtconfig", resource, loader);

    environment.getPropertySources().addFirst(propertySource);

    application.setEnvironment(environment);
  }

  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    System.out.println(context.isActive());
  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {

  }

  @Override
  public void started(ConfigurableApplicationContext context) {

  }

  @Override
  public void running(ConfigurableApplicationContext context) {

  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {

  }

  @Override
  public int getOrder() {
    return 1;
  }

  private static PropertySource<?> loadProperties(String propertySourceName, Resource resource,
                                                  PropertySourceLoader loader) {
    if (!resource.exists()) {
      throw new IllegalArgumentException("Resource " + resource + " does not exist");
    }
    try {
      List<PropertySource<?>> propertySources = loader.load(propertySourceName, resource);
      if(CollectionUtils.isEmpty(propertySources)) {
        return null;
      }
      return propertySources.get(0);
    } catch (IOException ex) {
      throw new IllegalStateException(
        "Failed to load custom configuration from " + resource, ex);
    }
  }

  private ConfigurableEnvironment createEnvironment() {
    if (application.getWebApplicationType() == WebApplicationType.SERVLET) {
      return new StandardServletEnvironment();
    }
    return new StandardEnvironment();
  }
}
