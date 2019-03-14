package com.gxk.demo.ext.registry;

import java.util.List;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.Registry;

public class InjvmRegistry implements Registry {

  @Override
  public URL getUrl() {
    return null;
  }

  @Override
  public boolean isAvailable() {
    return false;
  }

  @Override
  public void destroy() {

  }

  @Override
  public void register(URL url) {

  }

  @Override
  public void unregister(URL url) {

  }

  @Override
  public void subscribe(
      URL url,
      NotifyListener listener
  ) {

  }

  @Override
  public void unsubscribe(
      URL url,
      NotifyListener listener
  ) {

  }

  @Override
  public List<URL> lookup(URL url) {
    return null;
  }
}
