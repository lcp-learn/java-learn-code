package com.lcp.learn.spring.spb.controller;

import com.lcp.learn.spring.spb.beans.User;
import com.lcp.learn.spring.spb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController implements ApplicationContextAware {

  private final Logger logger = LoggerFactory.getLogger(SimpleController.class);

  @Autowired
  private UserService userService;

  private ApplicationContext applicationContext;

  @RequestMapping(value = "/simple", method = RequestMethod.POST)
  public User hello(
      @RequestBody User user,
      @RequestParam(required = false, defaultValue = "1") int size) {

    User target = userService.getUser(user.getName());
    try {
      logger.info("target:{}", target.getAge());
    } catch (Exception ignored) {
    }

    return target;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
