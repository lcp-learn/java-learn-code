package com.lcp.learn.dubbo.config;

import com.lcp.learn.dubbo.provider.CatSwimApiImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-09:06
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry beanDefinitionRegistry) {

    System.out.println("importingClassMetadata = " + importingClassMetadata);
    System.out.println("beanDefinitionRegistry = " + beanDefinitionRegistry);

    BeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClassName(CatSwimApiImpl.class.getName());

    beanDefinitionRegistry.registerBeanDefinition("aa", beanDefinition);


  }
}
