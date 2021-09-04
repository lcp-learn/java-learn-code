package com.lcp.learn.spring.spb.webflux.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebHandler;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/7-12:18
 */
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

  @Bean
  public WebHandler webHandler(ApplicationContext applicationContext) {
    return new DispatcherHandler(applicationContext);
  }

}
