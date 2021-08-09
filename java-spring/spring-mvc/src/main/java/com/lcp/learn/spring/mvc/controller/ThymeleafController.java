package com.lcp.learn.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/7-13:39
 */
@Controller
public class ThymeleafController {

  @RequestMapping(value = "/th")
  public String hello(Model model) {
    model.addAttribute("message", "Spring MVC Thymeleaf Hello World Example!!");
    return "haha";
  }
}
