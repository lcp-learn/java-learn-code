package com.lcp.learn.spring.base.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/8/10-17:54
 */
public class MyApplicationContext extends AbstractApplicationContext implements ApplicationContext {

  @Override
  protected void refreshBeanFactory() throws BeansException, IllegalStateException {

  }

  @Override
  protected void closeBeanFactory() {

  }

  @Override
  public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
    return null;
  }
}
