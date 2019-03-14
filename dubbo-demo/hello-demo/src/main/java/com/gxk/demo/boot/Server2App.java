package com.gxk.demo.boot;

import com.gxk.demo.service.WorldService;
import com.gxk.demo.service.WorldServiceImpl;
import java.io.IOException;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Server2App {

  public static void main(String[] args) throws IOException {
    ServiceConfig<WorldServiceImpl> service = new ServiceConfig<>();

    ApplicationConfig application = new ApplicationConfig("dubbo-demo-api-provider");
    application.setQosEnable(false);
    service.setApplication(application);

    RegistryConfig registry = new RegistryConfig("multicast://224.5.6.7:1234");
    registry.setCheck(false);
    service.setRegistry(registry);

    service.setProtocol(new ProtocolConfig("injvm"));

    service.setInterface(WorldService.class);
    service.setRef(new WorldServiceImpl());
    service.export();

    ReferenceConfig<WorldService> reference = new ReferenceConfig<>();
    reference.setInterface(WorldService.class);
    WorldService service2 = reference.get();
    String message = service2.hello("dubbo");
    System.out.println(message);
  }
}
