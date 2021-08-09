package com.lcp.learn.servlet.web.annotation.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-14:18
 */
@WebListener
public class MyListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("sce = " + sce);
  }
}
