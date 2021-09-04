package com.lcp.learn.dubbo.spb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/20-17:02
 */
@RestController
@RequestMapping("/mono")
public class MonoController {

  private final Logger logger = LoggerFactory.getLogger(MonoController.class);

  @Resource
  private HelloApi helloApi;

  @RequestMapping(value = "/getUser", method = {POST, GET})
  public Mono<User> getUser(String name, Sex sex) {
    logger.info("mono getUser name:{},sex:{}", name, sex);
    return Mono.just(helloApi.hello(name, sex));
  }

  @RequestMapping(value = "/getUser2", method = {POST, GET})
  public Mono<User> getUser2(String name, Sex sex) {

    return Mono.create(monoSink -> {

      logger.info("mono getUser2 name:{},sex:{}", name, sex);
      monoSink.success(helloApi.hello(name, sex));
    });

  }

}
