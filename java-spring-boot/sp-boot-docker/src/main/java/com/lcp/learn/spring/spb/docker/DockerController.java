package com.lcp.learn.spring.spb.docker;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.docker.beans.User;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/docker")
public class DockerController {

  private final Logger logger = LoggerFactory.getLogger(DockerController.class);

  @RequestMapping(value = "/check", method = {GET, POST})
  public User check(@RequestParam(required = false) Integer age) {

    User newUser = new User();
    newUser.setAge(Optional.ofNullable(age).orElse(RandomUtils.nextInt(1, 99)));
    newUser.setName(RandomStringUtils.random(5, true, true));

    logger.info("target:{}", JSON.toJSONString(newUser));
    logger.info("target:{}", JSON.toJSONString(newUser));
    logger.info("target:{}", JSON.toJSONString(newUser));
    logger.info("target:{}", JSON.toJSONString(newUser));
    return newUser;
  }

}
