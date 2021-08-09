package com.lcp.learn.spring.base.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-16:50
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  private final Logger logger = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);

  /**
   * Modify the application context's internal bean definition registry after its standard initialization. All regular bean definitions will have been
   * loaded, but no beans will have been instantiated yet. This allows for adding further bean definitions before the next post-processing phase kicks
   * in.
   *
   * @param registry the bean definition registry used by the application context
   * @throws BeansException in case of errors
   */
  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    logger.info("registry==========:{}", registry.getClass().getName());
  }

  /**
   * Modify the application context's internal bean factory after its standard initialization. All bean definitions will have been loaded, but no
   * beans will have been instantiated yet. This allows for overriding or adding properties even to eager-initializing beans.
   *
   * @param beanFactory the bean factory used by the application context
   * @throws BeansException in case of errors
   */
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    logger.info("beanFactory2222==========:{}", beanFactory.getClass().getName());
  }
}
