package com.lcp.learn.spring.base.startup.xml;

import com.lcp.learn.spring.base.beans.Goods;
import com.lcp.learn.spring.base.service.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-11:07
 */
public class SpringRegistBeanMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringRegistBeanMain.class);

  public static void main(String[] args) {

    GenericApplicationContext applicationContext = new GenericApplicationContext();
    applicationContext.refresh();

    String beanTarget = "target1";
    applicationContext.registerBean(beanTarget, HelloServiceImpl.class);
    Object hello = applicationContext.getBean(beanTarget);
    System.out.println("hello = " + hello.getClass().getName());

    applicationContext.registerBean(beanTarget, Goods.class);
    hello = applicationContext.getBean(beanTarget);
    System.out.println("hello = " + hello.getClass().getName());

  }

}
