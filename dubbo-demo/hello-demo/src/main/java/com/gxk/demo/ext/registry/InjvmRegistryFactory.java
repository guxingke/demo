package com.gxk.demo.ext.registry;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;

public class InjvmRegistryFactory implements RegistryFactory {

  @Override
  public Registry getRegistry(URL url) {
    return new InjvmRegistry();
  }
}
