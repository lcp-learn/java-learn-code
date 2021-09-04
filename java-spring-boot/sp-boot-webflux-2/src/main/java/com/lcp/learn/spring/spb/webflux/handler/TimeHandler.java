package com.lcp.learn.spring.spb.webflux.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TimeHandler {

  public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
    return ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(
            Mono.just("Now is " + LocalDateTime.now().toString()),
            String.class);
  }

  public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
    return ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(
            Mono.just("Today is " + LocalDateTime.now().toString()),
            String.class);
  }

  public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
    return ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(
            Flux.interval(Duration.ofSeconds(1))
                .map(l -> LocalDateTime.now().toString()),
            String.class);
  }
}
