package com.lcp.learn.spring.spb.controller;

import com.lcp.learn.spring.spb.beans.User;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/20-19:46
 */
@RestController
@RequestMapping(path = "/session")
public class SessionController {

  private final Logger logger = LoggerFactory.getLogger(SessionController.class);

  /**
   * session测试
   *
   * @param request
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public Map<String, String> addSession(
      HttpServletRequest request,
      @RequestParam(value = "userName") String userName) {

    HttpSession session = request.getSession();
    String sessionId = session.getId();
    String requestURI = request.getRequestURI() + ":" + request.getServerPort();

    User user = new User(userName, RandomUtils.nextInt(10, 99));

    // 向session中保存用户信息 key规则： user + "_" + uid
    request.getSession().setAttribute(userName, user);

    Map<String, String> sessionInfoMap = new HashMap<>(2);
    sessionInfoMap.put("sessionId", sessionId);
    sessionInfoMap.put("requestURI", requestURI);

    logger.info("info:{}", sessionInfoMap);

    return sessionInfoMap;
  }

  /**
   * session测试
   *
   * @param request
   * @return
   */
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public Map<String, Object> getSession(
      HttpServletRequest request,
      @RequestParam(value = "userName") String userName) {

    HttpSession session = request.getSession();
    String sessionId = session.getId();
    String requestURI = request.getRequestURI() + ":" + request.getServerPort();

    Map<String, Object> sessionInfoMap = new HashMap<String, Object>(2);

    // 获取session中uid为1的用户的信息
    User user = (User) request.getSession().getAttribute(userName);

    sessionInfoMap.put("sessionId", sessionId);
    sessionInfoMap.put("requestURI", requestURI);
    sessionInfoMap.put("userInfo", user);

    logger.info("info:{}", sessionInfoMap);

    return sessionInfoMap;
  }

}
