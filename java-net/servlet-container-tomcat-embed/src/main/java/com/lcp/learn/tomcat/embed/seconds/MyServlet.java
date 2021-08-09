package com.lcp.learn.tomcat.embed.seconds;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-15:28
 */
public class MyServlet extends HttpServlet {

  private static final long serialVersionUID = 7359778948930665576L;

  @Override
  protected void doGet(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws ServletException, IOException {

    System.out.println("httpServletRequest = get");
  }

  @Override
  protected void doPost(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws ServletException, IOException {

    System.out.println("httpServletRequest = post");
  }
}
