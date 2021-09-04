package com.lcp.learn.spring.spb.webflux.controller;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.webflux.beans.User;
import com.lcp.learn.spring.spb.webflux.beans.UserBuilder;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-11:13
 */
@RestController
@RequestMapping("/react")
public class ReactiveController {

  private final Logger logger = LoggerFactory.getLogger(ReactiveController.class);

  @GetMapping("/hello")
  public Mono<User> hello() {

    User user = UserBuilder.anUser()
        .name("张三")
        .age(87)
        .build();

    logger.info("user:{}", JSON.toJSONString(user));

    return Mono.just(user).delayElement(Duration.of(10, ChronoUnit.SECONDS));

  }

  @GetMapping(value = "/flux3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  private Flux<String> flux() {

    logger.info("finfished");

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    return Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "flux data--" + i + "--" + format.format(new Date());
    }));
  }

}
