package com.gxk.demo.boot;

import com.gxk.demo.service.HelloService;
import com.gxk.demo.service.HelloServiceImpl;
import java.io.IOException;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

public class GenericCallApp {

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
    service.export();

    ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
    reference.setInterface("com.gxk.demo.service.HelloService");
    reference.setGeneric(true);
    reference.setScope("local");

    GenericService gs = ReferenceConfigCache.getCache().get(reference);

    Object hello = gs.$invoke("hello", new String[]{"java.lang.String"}, new Object[]{"hello"});
    System.out.println(hello);
  }
}
