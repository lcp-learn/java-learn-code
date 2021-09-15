package com.lcp.learn.spring.spb;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lcp
 * @since 2019/9/16-15:46
 */
@SpringBootApplication
@EnableCaching
public class SpbMain {

  private static final Logger logger = LoggerFactory.getLogger(SpbMain.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpbMain.class)
        .bannerMode(OFF)
        .web(SERVLET)
        // .web(NONE)
        .run(args);
    // var names = applicationContext.getBeanNamesForType(Executor.class);
    // Arrays.stream(names).forEach(name -> logger.info("name:{}", name));
    // var executor = applicationContext.getBean("applicationTaskExecutor");
    // logger.info("class:{}", executor.getClass().getName());

  }
}
