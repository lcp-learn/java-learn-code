package com.lcp.learn.tomcat.embed.seconds;

import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-15:17
 */
public class SimpleTomcat {

  public static void main(String[] args) throws LifecycleException {

    String path = "/Users/lichunpeng/tmp/tmp_data/tomcat/";

    Tomcat tomcat = new Tomcat();//创建tomcat实例，用来启动tomcat
    tomcat.setHostname("localhost");//设置主机名
    tomcat.setPort(8080);//设置端口
    tomcat.setBaseDir(path);//tomcat存储自身信息的目录，比如日志等信息，根目录

    Connector connector = new Connector(Http11NioProtocol.class.getName());//设置协议，默认就是这个协议
    connector.setURIEncoding("UTF-8");//设置编码
    connector.setPort(8765);//设置端口

    Connector connector2 = new Connector(Http11NioProtocol.class.getName());//设置协议，默认就是这个协议
    connector2.setURIEncoding("UTF-8");//设置编码
    connector2.setPort(8763);//设置端口

    tomcat.getService().addConnector(connector2);
    tomcat.getService().addConnector(connector);

    Context context = tomcat.addContext("/myapp", null);//网络访问路径
    Tomcat.addServlet(context, "myServlet", new MyServlet()); //配置servlet
    Tomcat.addServlet(context, "myServlet2", new MyServlet()); //配置servlet
    context.addServletMappingDecoded("/messageServlet", "myServlet");//配置servlet映射路径
    context.addServletMappingDecoded("/messageServlet2", "myServlet2");//配置servlet映射路径

    // StandardServer standardServer = (StandardServer) tomcat.getServer();//添加监听器，不知何用
    // AprLifecycleListener listener = new AprLifecycleListener();
    // standardServer.addLifecycleListener(listener);

    //设置appBase为项目所在目录
    tomcat.getHost().setAppBase(System.getProperty("user.dir") + File.separator + ".");

    //设置WEB-INF文件夹所在目录
    //该文件夹下包含web.xml
    //当访问localhost:端口号时，会默认访问该目录下的index.html/jsp页面
    // tomcat.addWebapp("", "webapp");
    tomcat.start();//启动tomcat

    tomcat.getServer().await();//维持tomcat服务，否则tomcat一启动就会关闭

  }
}
