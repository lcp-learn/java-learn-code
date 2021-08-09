package com.lcp.learn.spring.web.container.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-11:22
 */
@Configuration
@ComponentScan({"com.lcp.learn.spring.web.container"})
public class AppConfiguration {

  private final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

  @Bean
  public ExecutorService buildThreadPool() {
    logger.info("buildThreadPool = ");

    ExecutorService threadpool = Executors.newFixedThreadPool(5);
    return threadpool;
  }
}
