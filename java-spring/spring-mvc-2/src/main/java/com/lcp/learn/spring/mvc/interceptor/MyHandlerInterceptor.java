package com.lcp.learn.spring.mvc.interceptor;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    String pathInfo = request.getPathInfo();
    String contextPath = request.getContextPath();
    String servletPath = request.getServletPath();

    logger.info("pathInfo:{},contextPath:{},servletPath:{},preHandle handler = {}",
        pathInfo, contextPath, servletPath, handlerMethod.getMethod().getName());

    response.setCharacterEncoding("UTF-8");
    response.setLocale(Locale.CHINA);

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("postHandle handler = {}", handler.getClass().getName());
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    logger.info("afterCompletion handler = {}", handler.getClass().getName());
  }
}
