package com.lcp.learn.servlet.web.simple.listeners;

import com.lcp.learn.servlet.web.simple.filters.CheckFilter;
import com.lcp.learn.servlet.web.simple.filters.MyFilter;
import com.lcp.learn.servlet.web.simple.servlets.SecondServlet;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-10:52
 */
public class MyListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    ServletContext servletContext = servletContextEvent.getServletContext();

    try {

      // 添加servlet
      servletContext.addServlet("second", servletContext.createServlet(SecondServlet.class));
      servletContext.getServletRegistration("second").addMapping("/second");

      // 添加filter
      servletContext.addFilter("myf", servletContext.createFilter(MyFilter.class));
      servletContext.addFilter("check", servletContext.createFilter(CheckFilter.class));

      servletContext.getFilterRegistration("check")
          .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/hello");
      servletContext.getFilterRegistration("myf")
          .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/hello");

    } catch (ServletException e) {
      e.printStackTrace();
    }

  }
}
