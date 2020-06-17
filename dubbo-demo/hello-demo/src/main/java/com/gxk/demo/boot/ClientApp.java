package com.gxk.demo.boot;

import com.mng.rpc.server.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class ClientApp {
  public static void main(String[] args) {
    ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-consumer");
    application.setQosEnable(false);

    reference.setApplication(application);
//    reference.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
    reference.setInterface(HelloService.class);
    reference.setUrl("dubbo://127.0.0.1:20888");
    HelloService service = reference.get();
    String message = service.hello("dubbo");
    System.out.println(message);
  }
}
