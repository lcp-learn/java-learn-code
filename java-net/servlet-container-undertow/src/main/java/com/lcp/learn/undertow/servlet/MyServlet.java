package com.lcp.learn.undertow.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:    <br/>
 *
 * @author xyy
 * @since 2019/9/16-16:05
 */
public class MyServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    doPost(httpServletRequest, httpServletResponse);
  }

  @Override
  protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse resp) {
    System.out.println("httpServletRequest = " + httpServletRequest);
  }
}
