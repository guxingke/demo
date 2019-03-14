package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import java.io.IOException;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class BugServerApp {
  public static void main(String[] args) throws IOException {
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("multicast://224.5.6.7:1234");
    registry.setCheck(false);
    service.setRegistry(registry);

    service.setProtocol(new ProtocolConfig("dubbo"));

    service.setInterface(HelloService.class);
    service.setRef(new HelloServiceImpl());
    service.setGroup("test");
    service.export();

    System.in.read();
  }
}
