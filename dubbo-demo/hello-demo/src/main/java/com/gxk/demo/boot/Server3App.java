package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import java.io.IOException;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Server3App {

  public static void main(String[] args) throws IOException {
    ServiceConfig<HelloServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("zookeeper://localhost:2181");
    registry.setCheck(true);
    service.setRegistry(registry);

//    ProtocolConfig protocolConfig = new ProtocolConfig();
//    protocolConfig.setHost("127.0.0.1");
//    service.setProtocol(protocolConfig);

    service.setInterface(HelloService.class);
    service.setRef(new HelloServiceImpl());
    service.export();

    System.in.read();
  }
}
