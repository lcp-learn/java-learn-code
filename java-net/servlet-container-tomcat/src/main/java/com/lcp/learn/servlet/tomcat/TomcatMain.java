package com.lcp.learn.servlet.tomcat;

import org.apache.catalina.startup.Bootstrap;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-16:38
 */
public class TomcatMain {

  public static void main(String[] args) {

    // Catalina catalina = new Catalina();
    // catalina.start();

    Bootstrap bootstrap = new Bootstrap();
    try {
      bootstrap.start();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
