package com.lcp.learn.spring.base.service.impl;

import com.lcp.learn.spring.base.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-15:40
 */
// @Service
public class AServiceImpl implements AService,
    BeanNameAware,
    InitializingBean,
    DisposableBean,
    ApplicationContextAware {

  private ApplicationContext applicationContext;
  private final Logger logger = LoggerFactory.getLogger(AServiceImpl.class);

  @Override
  public boolean check(String name) {
    return false;
  }

  @Override
  public String check2(String name) {
    return name + "->haha";
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // logger = LoggerFactory.getLogger(AServiceImpl.class);
    logger.info("afterPropertiesSet");
  }

  @Override
  public void destroy() throws Exception {
    logger.info("destroy");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void setBeanName(String name) {
    logger.info("bean name:{}", name);
  }
}
