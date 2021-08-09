package com.lcp.learn.spring.mvc.controller.a;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.lcp.learn.spring.mvc.beans.User;
import com.lcp.learn.spring.mvc.controller.AbstractController;
import com.lcp.learn.spring.mvc.controller.request.UserRequest;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
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
public class WorldAController extends AbstractController {

  @RequestMapping(value = "/world", method = {POST, GET})//, produces = "application/json; charset=utf-8"
  public User world(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      @RequestParam(value = "name", required = false, defaultValue = "haha") String name,
      @RequestParam(value = "address") String address) {

    ServletContext servletContext = httpServletRequest.getServletContext();

    String value = "world A " + name + "," + address;
    logger.info("applicationContext = {}", applicationContext.hashCode());
    // ApplicationContext parentApplicationContext = applicationContext.getParent();
    // logger.info("parentApplicationContext = {}", parentApplicationContext.hashCode());
    logger.info("myService.getApplicationContext = {}", myService.getApplicationContext().hashCode());

    User user = new User(value, applicationContext.hashCode());

    // try {
    //   PrintWriter printWriter = httpServletResponse.getWriter();
    //   printWriter.print(JSON.toJSONString(user));
    //   printWriter.flush();
    //
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }

    String result = myService.haha(null);
    logger.info("result:{}", result);

    return user;
  }

  @Resource
  private Validator userValidator;

  @RequestMapping(value = "/second", method = {POST, GET}, produces = "application/json; charset=utf-8")//
  public String second(@RequestBody UserRequest userRequest, BindingResult result) {

    userValidator.validate(userRequest, result);

    logger.info("result:{}", result);
    if (result.hasErrors()) {
      List<ObjectError> errors = result.getAllErrors();
      for (ObjectError error : errors) {
        System.out.println(error.getDefaultMessage());
      }
    }

    return "second_" + userRequest.getAddress();
  }

}