package com.lcp.learn.spring.mvc.controller;

import com.lcp.learn.spring.mvc.service.MyService;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected Environment environment;
  protected ApplicationContext applicationContext;

  @Resource
  protected MyService myService;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
    logger.info("environment ========================== {}", environment.getClass().getName());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    logger.info("applicationContext ===================== {}", applicationContext.getClass().getName());

    Map<String, HandlerMapping> handlerMapping = applicationContext.getBeansOfType(HandlerMapping.class);

    handlerMapping.forEach((k, v) -> logger.info("key:{},value:{} ========= ", k, v));

  }
}
