package com.lcp.learn.spring.cloud.consumer.controller;

import com.lcp.learn.spring.cloud.api.beans.User;
import com.lcp.learn.spring.cloud.consumer.impl.CloudActionProviderClient;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-16:12
 */
@RestController
@RequestMapping("/")
public class ConsumerController {

  private final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CloudActionProviderClient cloudActionProviderClient;

  @RequestMapping("/sayHello")
  public User sayHello(@RequestParam String name,
      @RequestParam int age) {

    //轻依赖
    Map<String, String> map = new HashMap<String, String>();
    map.put("name", name);
    String url = "http://spring-cloud-provider/sayHello?name={name}";
    String result1 = restTemplate.getForEntity(url, String.class, map).getBody();
    logger.info("result1:{}", result1);

    // 重依赖
    String result = cloudActionProviderClient.sayHello(name);
    logger.info("result:{}", result);
    User user = cloudActionProviderClient.createUser(name, age);
    logger.info("user:{}", user.hashCode());
    int code = cloudActionProviderClient.getCode(user);
    logger.info("code:{}", code);

    return user;
  }


}
