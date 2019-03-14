package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import java.io.IOException;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class ServerWithInjvmRegistryApp {
  public static void main(String[] args) throws IOException {
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider3");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("injvm://127.0.0.1");
    registry.setCheck(false);
    service.setRegistry(registry);

    service.setProtocol(new ProtocolConfig("injvm"));

    service.setInterface(HelloService.class);
    service.setRef(new HelloServiceImpl());
    service.export();

    ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
    reference.setInterface(HelloService.class);
    HelloService service2 = reference.get();
    String message = service2.hello("dubbo");
    System.out.println(message);
  }
}
