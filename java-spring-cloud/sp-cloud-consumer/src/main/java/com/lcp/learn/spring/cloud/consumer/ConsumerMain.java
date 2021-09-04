package com.lcp.learn.spring.cloud.consumer;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-16:07
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMain {

  private static final Logger logger = LoggerFactory.getLogger(ConsumerMain.class);

  public static void main(String[] args) {

    ConfigurableApplicationContext springApplicationBuilder = new SpringApplicationBuilder(ConsumerMain.class)
        .web(SERVLET)
        .bannerMode(OFF)
        .run(args);

    Runtime.getRuntime().addShutdownHook(new Thread(() ->
        logger.info("provider close....")));
  }
}
