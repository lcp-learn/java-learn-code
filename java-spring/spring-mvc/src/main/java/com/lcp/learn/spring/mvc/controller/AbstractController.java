package com.lcp.learn.spring.mvc.controller;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerMapping;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/6-15:56
 */
public abstract class AbstractController implements EnvironmentAware, ApplicationContextAware {

  private Environment environment;
  private ApplicationContext applicationContext;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
    System.out.println("environment ========================== " + environment.getClass().getName());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    System.out.println("applicationContext ===================== " + applicationContext.getClass().getName());

    Map<String, HandlerMapping> handlerMapping = applicationContext.getBeansOfType(HandlerMapping.class);

    handlerMapping.forEach((k, v) -> System.out.println("key:" + k + ",value:" + v + " = ====================="));

  }
}
