package com.lcp.learn.spring.cloud.provider.controller;

import com.google.gson.Gson;
import com.lcp.learn.spring.cloud.api.beans.User;
import com.lcp.learn.spring.cloud.api.facade.CloudAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-15:59
 */
@RestController
public class SimpleProviderController implements CloudAction {

  private final Logger logger = LoggerFactory.getLogger(SimpleProviderController.class);

  private Gson gson = new Gson();

  @Override
  public String sayHello(String name) {
    String value = "hello," + name;
    logger.info("sayHello name:{}", name);
    return value;
  }

  @Override
  @Cacheable(value = "simple-provider-cache", key = "#name + #age", sync = true)
  public User createUser(String name, int age) {
    User user = new User(name, age);
    logger.info("createUser user:{}", gson.toJson(user));
    return user;
  }

  @Override
  public int getCode(User user) {
    return user.hashCode();
  }
}
