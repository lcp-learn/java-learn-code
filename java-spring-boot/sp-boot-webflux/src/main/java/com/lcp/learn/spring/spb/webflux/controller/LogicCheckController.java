package com.lcp.learn.spring.spb.webflux.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lcp.learn.spring.spb.webflux.beans.User;
import com.lcp.learn.spring.spb.webflux.beans.UserBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-11:13
 */
@RestController
@RequestMapping("/logic")
public class LogicCheckController {

  private final Logger logger = LoggerFactory.getLogger(LogicCheckController.class);

  @GetMapping("/getAllUser")
  public Mono<List<User>> hello() {

    return Mono.fromFuture(CompletableFuture.supplyAsync((Supplier<List<User>>) () -> {
      User user = UserBuilder.anUser().name("张三").age(87).build();
      return Lists.newArrayList(user);
    }).thenApply(users -> {
      logger.info("getAllUser list:{}", JSON.toJSONString(users));
      return users;
    }));
  }

  @GetMapping("/getAllUser2")
  public Mono<List<User>> hello2() {

    return Mono.fromCallable(() -> {

      User user = UserBuilder.anUser().name("张三").age(87).build();
      List<User> list = Lists.newArrayList(user);
      logger.info("getAllUser2 list:{}", JSON.toJSONString(user));

      return list;
    });
  }


}
