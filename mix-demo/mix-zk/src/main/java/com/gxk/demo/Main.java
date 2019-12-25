package com.gxk.demo;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Main {

  // 清理dubbo服务治理规则.
  public static void main(String[] args) {
    CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString("10.1.4.10:3881")
        .sessionTimeoutMs(1000)
        .connectionTimeoutMs(1000)
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();

    client.start();

    try {
      client.getChildren().forPath("/dubbo").stream()
          .forEach(it -> {
            try {
              String subPath = "/dubbo/" + it + "/configurators";
              client.getChildren().forPath(subPath).forEach(cit -> {
                System.out.println(cit);
                try {
                  String subPath2 = subPath + "/" + cit;
                  client.delete().forPath(subPath2);
                  System.out.println(subPath2);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              });
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      client.close();
    }
  }
}
