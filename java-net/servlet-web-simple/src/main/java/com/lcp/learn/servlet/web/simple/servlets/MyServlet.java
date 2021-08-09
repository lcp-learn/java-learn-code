package com.lcp.learn.servlet.web.simple.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/8/31-16:02
 */
public class MyServlet extends HttpServlet {

  private static final long serialVersionUID = 5371751116244822505L;

  @Override
  protected void doGet(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws IOException {
    doPost(httpServletRequest, httpServletResponse);
  }

  @Override
  protected void doPost(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws IOException {

    Thread.dumpStack();

    System.out.println("httpServletRequest = " + httpServletRequest.getClass().getName());
    System.out.println("httpServletResponse = " + httpServletResponse.getClass().getName());
    ServletContext servletContext = httpServletRequest.getServletContext();
    System.out.println("servletContext = " + servletContext.getClass().getName());

    System.out.println("	servletContext.getVirtualServerName();	" + servletContext.getVirtualServerName());
    System.out.println("	servletContext.getServletNames();	" + servletContext.getServletNames());
    System.out.println("	servletContext.getServletRegistrations();	" + servletContext.getServletRegistrations());
    System.out.println("	servletContext.getServlets();	" + servletContext.getServlets());

    httpServletRequest.getHeaderNames().asIterator().forEachRemaining(item ->
        System.out.println("item = " + item + ":\t" + httpServletRequest.getHeader(item))
    );

    // writer
    PrintWriter printWriter = httpServletResponse.getWriter();
    printWriter.write(servletContext.getServletRegistrations().toString());
    printWriter.flush();

    ////         outputstream
    //        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
    //        servletOutputStream.write(servletContext.getServletRegistrations().toString().getBytes());

    HttpSession httpSession = httpServletRequest.getSession(true);
    System.out.println("httpSession = " + httpSession.getId());

    //        System.out.println("	servletContext. getAttributeNames();	" + servletContext.getAttributeNames());
    //        System.out.println("	servletContext.getClassLoader();	" + servletContext.getClassLoader());
    //        System.out.println("	servletContext.getContextPath();	" + servletContext.getContextPath());
    //        System.out.println("	servletContext.getDefaultSessionTrackingModes();	" + servletContext
    //        .getDefaultSessionTrackingModes());
    //        System.out.println("	servletContext.getEffectiveMajorVersion();	" + servletContext
    //        .getEffectiveMajorVersion());
    //        System.out.println("	servletContext.getEffectiveMinorVersion();	" + servletContext
    //        .getEffectiveMinorVersion());
    //        System.out.println("	servletContext.getEffectiveSessionTrackingModes();	" + servletContext
    //        .getEffectiveSessionTrackingModes());
    //        System.out.println("	servletContext.getFilterRegistrations();	" + servletContext.getFilterRegistrations
    //        ());
    //        System.out.println("	servletContext.getInitParameterNames();	" + servletContext.getInitParameterNames());
    //        System.out.println("	servletContext.getJspConfigDescriptor();	" + servletContext.getJspConfigDescriptor
    //        ());
    //        System.out.println("	servletContext.getMajorVersion();	" + servletContext.getMajorVersion());
    //        System.out.println("	servletContext.getMinorVersion();	" + servletContext.getMinorVersion());
    //        System.out.println("	servletContext.getRequestCharacterEncoding();	" + servletContext
    //        .getRequestCharacterEncoding());
    //        System.out.println("	servletContext.getResponseCharacterEncoding();	" + servletContext
    //        .getResponseCharacterEncoding());
    //        System.out.println("	servletContext.getServerInfo();	" + servletContext.getServerInfo());
    //        System.out.println("	servletContext.getServletContextName();	" + servletContext.getServletContextName());
    //        System.out.println("	servletContext.getSessionCookieConfig();	" + servletContext.getSessionCookieConfig
    //        ());
    //        System.out.println("	servletContext.getSessionTimeout();	" + servletContext.getSessionTimeout());

    //        ServletRegistration servletRegistration = servletContext.getServletRegistration("hello");
    //        servletRegistration.getMappings().forEach(item -> System.out.println("item = " + item));
    //        Servlet servlet;
    //        ServletConfig servletConfig;

  }
}
