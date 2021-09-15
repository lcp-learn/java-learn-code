package com.lcp.learn.spring.spb.webflux.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.webflux.beans.User;
import com.lcp.learn.spring.spb.webflux.services.MyService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-11:13
 */
@RestController
public class MessageController {

  private final Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Resource
  private MyService myService;

  @RequestMapping("/wf")
  public Flux<List<User>> allMessages(
      @RequestParam Map<String, String> param,
      @RequestParam(name = "count", defaultValue = "3") int count) {

    param.forEach((k, v) -> logger.info("k:{},v:{}", k, v));

    List<User> userList = myService.getAllUser(count);
    String message = JSON.toJSONString(userList);

    logger.info("data={}", message);

    return Flux.just(userList);
  }

  @RequestMapping("/wf2")
  public Mono<List<User>> allMessages2(
      @RequestParam(name = "count", defaultValue = "3") int count) {

    return Mono.fromCallable(() -> {
      List<User> userList = myService.getAllUser(count);
      logger.info("data2={}", JSON.toJSONString(userList));
      return userList;
    });
  }

  @RequestMapping("/wf3")
  public Mono<List<User>> allMessages3(
      @RequestParam(name = "count", defaultValue = "3") int count) {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    // 返回包含时间字符串的ServerResponse
    HandlerFunction<ServerResponse> timeFunction = request -> ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(Mono.just("Now is " + simpleDateFormat.format(new Date())), String.class);

    // 返回包含日期字符串的ServerResponse
    HandlerFunction<ServerResponse> dateFunction = request -> ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(Mono.just("Today is " + simpleDateFormat2.format(new Date())), String.class);

    RouterFunction<ServerResponse> routerFunction = RouterFunctions
        .route(GET("/time"), timeFunction)
        .andRoute(GET("/date"), dateFunction);

    return Mono.create(monoSink -> {
      List<User> userList = myService.getAllUser(count);
      monoSink.success(userList);
    });
  }
}
