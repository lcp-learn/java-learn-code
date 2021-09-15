package com.lcp.learn.spring.spb.controller;

import com.lcp.learn.spring.spb.beans.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/6/9-13:28
 */
@RestController
public class BaseController {

  @RequestMapping("/user")
  public User getUser() {

    User user = new User();
    user.setName("asads");
    user.setAge(333);
    return user;
  }
}
