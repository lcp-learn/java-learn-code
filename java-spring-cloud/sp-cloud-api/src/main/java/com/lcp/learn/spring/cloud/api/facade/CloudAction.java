package com.lcp.learn.spring.cloud.api.facade;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.lcp.learn.spring.cloud.api.beans.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-15:53
 */
public interface CloudAction {

  @RequestMapping(value = "/sayHello", method = GET)
  String sayHello(@RequestParam(value = "name") String name);

  @RequestMapping(value = "/createUser", method = GET)
  User createUser(@RequestParam(value = "name") String name,
      @RequestParam(value = "age") int age);

  @RequestMapping(value = "/getCode", method = POST)
  int getCode(@RequestBody User user);
}
