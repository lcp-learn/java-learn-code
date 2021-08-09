package com.lcp.learn.spring.mvc.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/7/21-16:09
 */
public class MyServiceImpl implements MyService, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public String haha(String name) {
    System.out.println("name = " + name);
    return name;
  }

  @Override
  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
