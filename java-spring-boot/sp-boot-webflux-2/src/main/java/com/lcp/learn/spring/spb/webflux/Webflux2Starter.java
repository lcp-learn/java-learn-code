package com.lcp.learn.spring.spb.webflux;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.REACTIVE;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/2-18:28
 */
@SpringBootApplication(scanBasePackages = "com.lcp.learn.spring.spb.webflux")
@EnableWebFlux
public class Webflux2Starter {

  public static void main(String[] args) {

    new SpringApplicationBuilder(Webflux2Starter.class)
        .web(REACTIVE)
        .bannerMode(OFF)
        .run(args);
  }
}
