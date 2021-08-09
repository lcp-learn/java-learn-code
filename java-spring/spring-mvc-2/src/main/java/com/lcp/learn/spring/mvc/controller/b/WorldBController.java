package com.lcp.learn.spring.mvc.controller.b;

import com.lcp.learn.spring.mvc.beans.User;
import com.lcp.learn.spring.mvc.controller.AbstractController;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
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
@RequestMapping("/world")
public class WorldBController extends AbstractController {

  @RequestMapping("/world")
  public User world(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      @RequestParam(value = "name", required = false, defaultValue = "haha") String name) {

    ServletContext servletContext = httpServletRequest.getServletContext();

    String value = "world B --> " + name;

    logger.info("applicationContext = {}", applicationContext.hashCode());
    ApplicationContext parentApplicationContext = applicationContext.getParent();
    logger.info("parentApplicationContext = {}", parentApplicationContext.hashCode());
    logger.info("myService.getApplicationContext = {}", myService.getApplicationContext().hashCode());

    return new User(value, applicationContext.hashCode());
  }

}