package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class ClientApp {
  public static void main(String[] args) {
    ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-consumer");
    application.setQosEnable(false);

    reference.setApplication(application);
    reference.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
    reference.setInterface(HelloService.class);
    HelloService service = reference.get();
    String message = service.hello("dubbo");
    System.out.println(message);
  }
}
