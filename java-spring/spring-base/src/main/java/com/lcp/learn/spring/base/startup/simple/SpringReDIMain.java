package com.lcp.learn.spring.base.startup.simple;

import com.lcp.learn.spring.base.config.SimpleConfig;
import com.lcp.learn.spring.base.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/23-18:13
 */
public class SpringReDIMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringReDIMain.class);

  public static void main(String[] args) {

    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext(SimpleConfig.class);
    String[] names = annotationConfigApplicationContext.getBeanDefinitionNames();
    for (String name : names) {
      logger.info("name = {}", name);
    }
    AService aService = annotationConfigApplicationContext.getBean("aService", AService.class);
    logger.info("class={}", aService.getClass().getName());

  }
}
