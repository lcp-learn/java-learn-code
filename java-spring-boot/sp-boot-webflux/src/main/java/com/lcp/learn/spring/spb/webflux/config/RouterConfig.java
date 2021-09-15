package com.lcp.learn.spring.spb.webflux.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.lcp.learn.spring.spb.webflux.handler.TimeHandler;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

  private final Logger logger = LoggerFactory.getLogger(RouterConfig.class);

  @Resource(name = "timeHandler")
  private TimeHandler timeHandler;

  @Bean
  public RouterFunction<ServerResponse> timerRouter() {

    logger.info("timerRouter config");

    return route(GET("/time1"), req -> timeHandler.getTime(req))
        .andRoute(GET("/date"), timeHandler::getDate)
        .andRoute(GET("/time2"), req -> timeHandler.getTime(req))
        .andRoute(GET("/time3"), timeHandler::sendTimePerSec);  // 这种方式相对于上一行更加简洁

    // return route().build()
    //                GET("/time"), req -> timeHandler.getTime(req)
    // .andRoute(GET("/date"), timeHandler::getDate)  // 这种方式相对于上一行更加简洁
    // .andRoute(GET("/time"), req -> timeHandler.getTime(req))
    // .andRoute(GET("/times"), timeHandler::sendTimePerSec);  // 增加这一行
  }
}
