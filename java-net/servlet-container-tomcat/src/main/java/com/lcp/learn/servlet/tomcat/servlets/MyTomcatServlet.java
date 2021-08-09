package com.lcp.learn.servlet.tomcat.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-16:37
 */
public class MyTomcatServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("httpServletRequest = " + httpServletRequest);
  }
}
