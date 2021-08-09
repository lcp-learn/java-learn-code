package com.lcp.learn.spring.base.startup.xml;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.base.beans.User;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-11:07
 */
public class SpringXmlBeanFactoryMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringXmlBeanFactoryMain.class);

  public static void main(String[] args) {

    GenericBeanDefinition user2BeanDefinition = new GenericBeanDefinition();
    user2BeanDefinition.setBeanClassName(User.class.getName());
    user2BeanDefinition.setInitMethodName("init");
    user2BeanDefinition.setPropertyValues(new MutablePropertyValues(Collections.singletonMap("key", "asdasdas")));

    DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
    defaultListableBeanFactory.registerBeanDefinition("user2", user2BeanDefinition);

    Object obj = defaultListableBeanFactory.getBean("user2");
    logger.info("clazzName = " + obj.getClass().getName());
    logger.info("instances = " + JSON.toJSONString(obj));

    MessageSource messageSource =
        new ClassPathXmlApplicationContext("beans.xml");

  }

}
