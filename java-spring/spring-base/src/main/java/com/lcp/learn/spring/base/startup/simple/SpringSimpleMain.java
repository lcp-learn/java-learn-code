package com.lcp.learn.spring.base.startup.simple;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.base.beans.User;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/23-18:13
 */
public class SpringSimpleMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringSimpleMain.class);

  public static void main(String[] args) {

    GenericApplicationContext applicationContext = new GenericApplicationContext();

    applicationContext.refresh();

    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    beanDefinitionBuilder.setScope(SCOPE_SINGLETON);
    applicationContext.registerBeanDefinition("hahaha", beanDefinitionBuilder.getBeanDefinition());

    ConfigurableEnvironment environment = applicationContext.getEnvironment();
    logger.info("environment:{}", JSON.toJSONString(environment));

    Arrays.stream(applicationContext.getBeanFactory().getSingletonNames())
        .forEach(name -> logger.info("single name:{}", name));

    // var user = applicationContext.getBean(User.class);
    //
    // logger.info("user:{}", JSON.toJSONString(user));
    //
    Arrays.stream(applicationContext.getBeanDefinitionNames())
        .forEach(name -> logger.info("name:{}", name));

  }
}
