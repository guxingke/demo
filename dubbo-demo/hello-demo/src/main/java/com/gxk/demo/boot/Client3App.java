package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Client3App {

  public static void main(String[] args) {
    ReferenceConfig<HelloService> reference = new ReferenceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-consumer");
    application.setQosEnable(false);
    reference.setApplication(application);

    RegistryConfig registry = new RegistryConfig("zookeeper://localhost:2181");
    reference.setRegistry(registry);

    reference.setInterface(HelloService.class);
    HelloService service = reference.get();
    String message = service.hello("dubbo");
    System.out.println(message);
  }
}
