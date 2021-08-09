package com.lcp.learn.spring.mvc.annotation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/9-17:10
 */
@RestController
public class MyController {

  @RequestMapping("hello")
  public String hello(@RequestParam(name = "name", required = false, defaultValue = "haha") String name) {
    return "hello," + name;
  }
}
