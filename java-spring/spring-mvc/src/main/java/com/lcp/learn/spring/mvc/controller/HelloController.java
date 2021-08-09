package com.lcp.learn.spring.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.mvc.beans.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/8/26-15:46
 */
@Controller
public class HelloController {

  @RequestMapping("/hello")
  public ModelAndView hello(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      HttpSession httpSession,
      ModelMap modelMap,
      Model model,
      @ModelAttribute("user") User user,
      @RequestParam(value = "name", required = false, defaultValue = "haha") String name) {

    //        AsyncContext asyncContext = httpServletRequest.startAsync();
    //        asyncContext.dispatch("");

    System.out.println("httpSession = " + httpSession.getId());

    System.out.println("user ========== " + JSON.toJSONString(user));
    //        System.out.println("user.getCreateDate.getYear ========== " + user.getCreateDate().getYear());
    System.out.println("name ========== " + name);

    String value = "Hello " + name;
    System.out.println(value);

    ModelAndView modelAndView = new ModelAndView("index.jsp");
    modelAndView.addObject("message", value);

    return modelAndView;
  }

}
