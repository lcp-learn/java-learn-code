package com.lcp.learn.spring.base.config;

//import com.lcp.learn.spring.base.factory.AServiceFactoryBean;
//import com.lcp.learn.spring.base.service.AService;

import com.lcp.learn.spring.base.factory.AServiceFactoryBean;
import com.lcp.learn.spring.base.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-16:27
 */
@Configuration
@ComponentScan({
    "com.lcp.learn.spring.simple.service.impl",
    "com.lcp.learn.spring.simple.processors"
})
@PropertySource(value = {
    "classpath:haha.properties",
    "classpath:qq.properties"
})
public class SimpleConfig {

  private final Logger logger = LoggerFactory.getLogger(SimpleConfig.class);

  @Autowired
  private Environment environment;

  @Bean
  public AServiceFactoryBean aServiceFactoryBean() {
    logger.info("qq.name={}", environment.getProperty("qq.name"));
    return new AServiceFactoryBean();
  }

  @Bean
  public AService aService(AServiceFactoryBean aServiceFactoryBean) throws Exception {
    aServiceFactoryBean.setId(environment.getProperty("app.aservice.type", Integer.class));
    return aServiceFactoryBean.getObject();
  }
}
