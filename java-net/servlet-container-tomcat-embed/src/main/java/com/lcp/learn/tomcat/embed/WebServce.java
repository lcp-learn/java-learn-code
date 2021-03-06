package com.lcp.learn.tomcat.embed;

import java.io.File;
import java.io.IOException;
import org.apache.catalina.Host;
import org.apache.catalina.Server;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/5-18:08
 */
public class WebServce {

  //设置主机或ip
  private String hostname = "localhost";
  //设置端口,默认的端口，主要看配置属性
  private int port = 8899;
  //
  private String webappDir = "webapp";
  //设置 连接时的一些参数
  private int maxPostSize = 0;
  private int maxThreads = 200;
  private int acceptCount = 100;

  //tomcat引用
  private Tomcat tomcat;

  public WebServce() {
  }

  public static WebServce getWebserce() {
    return new WebServce();
  }

  //获取属性信息
  protected void loadProperties() throws IOException {

    Configuration config = null;

    try {
      config = new Configurations().properties(new File("webserver.properties"));
    } catch (Exception ce) {

    }

    //根据配置文件修改初始值
    //第二个参数是默认值，当第一个为空时，使用默认值
    this.hostname = config.getString("webserver.hostname", "localhost");
    this.port = config.getInt("webserver.port", 8899);
    this.webappDir = config.getString("webserver.webappDir", "webapp");
    this.maxPostSize = config.getInt("webserver.maxPostSize", 0);
    this.maxThreads = config.getInt("webserver.maxThreads", 200);
    this.acceptCount = config.getInt("webserver.acceptCount", 100);

  }

  //启动
  public void start() {
    try {
      //加载配置
      this.loadProperties();
      //tomcat实例
      this.tomcat = new Tomcat();
      this.tomcat.setPort(this.port);
      this.tomcat.setHostname(this.hostname);
      //tomcat存储自身信息，保存在项目目录下
      this.tomcat.setBaseDir(".");

      this.configServer(this.tomcat.getServer());
      this.tomcat.getEngine();
      this.configHost(this.tomcat.getHost());
      this.configConnector(this.tomcat.getConnector());
      //第一个参数上下文路径contextPath,第二个参数docBase
      this.tomcat.addWebapp("", System.getProperty("user.dir") + File.separator + this.webappDir);

      //这种方式也行
      //  this.tomcat.getHost().setAppBase(System.getProperty("user.dir")+ File.separator+".");
      //  this.tomcat.addWebapp("",this.webappDir);

      this.tomcat.start();

      this.tomcat.getServer().await();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void configHost(Host host) {
    //user.dir  用户的当前工作目录
    host.setAppBase(System.getProperty("user.dir"));
  }

  private void configServer(Server server) {
    AprLifecycleListener listener = new AprLifecycleListener();
    server.addLifecycleListener(listener);
  }

  //设置连接属性
  private void configConnector(Connector connector) {
    connector.setURIEncoding("UTF-8");
    connector.setMaxPostSize(this.maxPostSize);
    connector.setAttribute("maxThreads", Integer.valueOf(this.maxThreads));
    connector.setAttribute("acceptCount", Integer.valueOf(this.acceptCount));
    connector.setAttribute("disableUploadTimeout", Boolean.valueOf(true));
    connector.setAttribute("enableLookups", Boolean.valueOf(false));
  }
}
