package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class ServerWithoutRegistryApp {
  public static void main(String[] args) throws IOException {
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("N/A");
    registry.setCheck(false);
    service.setRegistry(registry);

    service.setProtocol(new ProtocolConfig("injvm"));

    service.setInterface(HelloService.class);
    service.setRef(new HelloServiceImpl());
    service.setGroup("test");
    service.export();

    ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
    reference.setInterface(HelloService.class);
    reference.setGroup("test");
    HelloService service2 = reference.get();
    String message = service2.hello("dubbo");
    System.out.println(message);
  }
}
