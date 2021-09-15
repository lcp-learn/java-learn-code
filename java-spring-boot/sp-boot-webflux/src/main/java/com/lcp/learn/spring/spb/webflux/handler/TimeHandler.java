package com.lcp.learn.spring.spb.webflux.handler;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component("timeHandler")
public class TimeHandler {

  private final Logger logger = LoggerFactory.getLogger(TimeHandler.class);

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

  public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(Mono.just("Now is " + simpleDateFormat.format(new Date())), String.class);
  }

  public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(Mono.just("Today is " + simpleDateFormat2.format(new Date())), String.class);
  }

  public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(Flux.interval(Duration.ofSeconds(1)).map(aLong -> {
              String result = simpleDateFormat.format(new Date());
              logger.info("aLong:{},result:{}", aLong, result);
              return result;
            })
            , String.class);
  }
}

// TODO-lichunpeng 去掉simpleDateFormatter 2021/1/20-17:57
// TODO-lichunpeng spring-boot-web 替换成webflux 2021/1/20-17:57
