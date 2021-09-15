package com.lcp.learn.spb.mybatis;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/14-14:18
 */
@SpringBootApplication
public class MybatisPlusMain {

  private static final Logger logger = LoggerFactory.getLogger(MybatisPlusMain.class);

  public static void main(String[] args) {

    ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(MybatisPlusMain.class)
        .bannerMode(OFF)
        .web(SERVLET)
        // .web(NONE)
        .run(args);

  }
}
