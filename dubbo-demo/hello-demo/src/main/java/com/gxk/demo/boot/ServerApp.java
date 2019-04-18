package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

// -Djava.net.preferIPv4Stack=true
public class ServerApp {
  public static void main(String[] args) throws IOException {
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("multicast://224.5.6.7:1234");
    registry.setCheck(false);
    service.setRegistry(registry);

    service.setProtocol(new ProtocolConfig());

    service.setInterface(HelloService.class);
    service.setRef(new HelloServiceImpl());
    service.export();

    System.in.read();
  }
}
