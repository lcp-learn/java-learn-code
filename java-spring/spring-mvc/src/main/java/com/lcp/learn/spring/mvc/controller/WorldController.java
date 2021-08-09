package com.lcp.learn.spring.mvc.controller;

import com.lcp.learn.spring.mvc.beans.User;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/8/26-15:46
 */
@RestController
public class WorldController extends AbstractController {

  @RequestMapping("/world")
  public User hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      @RequestParam(value = "name", required = false, defaultValue = "haha") String name) {

    ServletContext servletContext = httpServletRequest.getServletContext();

    String value = "world " + name;

    return new User();
  }

}