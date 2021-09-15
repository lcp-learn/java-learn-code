package com.lcp.learn.spring.spb.simple.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.simple.beans.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/simple")
public class SimpleController {

  private final Logger logger = LoggerFactory.getLogger(SimpleController.class);

  @RequestMapping(value = "/hello", method = POST)
  public User hello(
      @RequestBody User user,
      @RequestParam(required = false, defaultValue = "1") int size) {

    User newUser = new User();
    newUser.setAge(user.getAge() + RandomUtils.nextInt(1, 99));
    newUser.setName(user.getName() + RandomStringUtils.random(5, true, true));

    try {
      logger.info("newUser:{}", JSON.toJSONString(newUser));
    } catch (Exception ignored) {
    }

    return newUser;
  }

  @RequestMapping(value = "/check", method = GET)
  public User check() {

    User newUser = new User();
    newUser.setAge(RandomUtils.nextInt(1, 99));
    newUser.setName(RandomStringUtils.random(5, true, true));

    logger.info("target:{}", JSON.toJSONString(newUser));
    return newUser;
  }

  @RequestMapping(value = "/create1", method = POST)
  public User create1(
      @RequestParam("name") String name,
      @RequestParam("age") int age) {

    User user = new User(name, age);
    logger.info("create user:{}", JSON.toJSONString(user));

    return user;
  }

}
