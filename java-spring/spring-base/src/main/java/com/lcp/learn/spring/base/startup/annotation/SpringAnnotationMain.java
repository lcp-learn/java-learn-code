package com.lcp.learn.spring.base.startup.annotation;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.base.beans.User;
import com.lcp.learn.spring.base.config.SimpleConfig;
import com.lcp.learn.spring.base.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * desc:    <br/> ResourceBundleMessageSource
 *
 * @author lichunpeng
 * @since 2020/3/13-11:07
 */
public class SpringAnnotationMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringAnnotationMain.class);

  public static void main(String[] args) {

    // System.setProperty("spring.profiles.active", "haha2");

    // annotation1();
    annotation2();
  }

  private static void annotation2() {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
    configurableEnvironment.setActiveProfiles("haha");
    applicationContext.scan(
        "com.lcp.learn.spring.base.config",
        "com.lcp.learn.spring.base.processors");
    applicationContext.refresh();

    User user = applicationContext.getBean(User.class);

    logger.info("user:{}", JSON.toJSONString(user));
  }

  private static void annotation1() {

    AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(
        SimpleConfig.class);
    // var names = annotationContext.getBeanDefinitionNames();
    // for (String name : names) {
    //   logger.info("name = {}", name);
    // }
    AService aService = annotationContext.getBean("aService", AService.class);

    logger.info("class={}", aService.getClass().getName());

    String result = aService.check2("qwe");
    logger.info("main result={}", result);

  }

}
