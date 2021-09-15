package com.lcp.learn.spring.spbwar;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
    // DataSourceAutoConfiguration.class,
    FreeMarkerAutoConfiguration.class
})
@ComponentScan(basePackages = {
    "com.lcp.learn.spring.spbwar.config"
})
public class SpbWarApplication {

  protected static final Logger logger = LoggerFactory.getLogger(SpbWarApplication.class);

  public static void main(String[] args) {
    SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(SpbWarApplication.class);
    ConfigurableApplicationContext configurableApplicationContext = springApplicationBuilder
        .bannerMode(OFF)
        .web(SERVLET).run(args);

    Arrays.stream(configurableApplicationContext.getBeanDefinitionNames())
        .forEach(beanName -> logger.info("bean:{}", beanName));
  }

}
