package com.gxk.sbd;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    System.out.println("begin...");
    if (beanFactory instanceof BeanDefinitionRegistry) {
      final BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

      final String[] names = registry.getBeanDefinitionNames();
      for (String name : names) {
        final BeanDefinition bd = registry.getBeanDefinition(name);
        final boolean lazy = bd.isLazyInit();
        System.out.println(name + " : " + lazy);
      }
    }
    System.out.println("end...");
  }
}
