package com.lcp.learn.spring.base.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/4-13:05
 */
@Component
public class HahaFactoryBeanPostProcessor implements BeanFactoryPostProcessor {

  private final Logger logger = LoggerFactory.getLogger(HahaFactoryBeanPostProcessor.class);

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    logger.info("beanFactory==========:{}", beanFactory.getClass().getName());
  }
}
