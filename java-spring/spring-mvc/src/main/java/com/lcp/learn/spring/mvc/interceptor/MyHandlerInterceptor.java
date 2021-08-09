package com.lcp.learn.spring.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/6-18:01
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response,
      Object handler) throws Exception {

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    //        System.out.println("preHandle handler = " + handlerMethod.getClass().getName());

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) throws Exception {

    //        System.out.println("postHandle handler = " + handler.getClass().getName());
  }

  @Override
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) throws Exception {

    //        System.out.println("afterCompletion handler = " + handler.getClass().getName());
  }
}
