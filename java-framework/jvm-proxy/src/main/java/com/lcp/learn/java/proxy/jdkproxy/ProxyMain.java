package com.lcp.learn.java.proxy.jdkproxy;

import com.lcp.learn.java.proxy.api.Action;
import com.lcp.learn.java.proxy.jdkproxy.impl.JdkProxyImpl;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-09:01
 */
public class ProxyMain {

  private static final Logger logger = LoggerFactory.getLogger(ProxyMain.class);

  public static void main(String[] args) {

    // forClazz();

    forInterfaces();
  }

  /**
   * 针对接口
   */
  private static void forInterfaces() {

    var myAction = (Action) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), new Class<?>[]{Action.class}
        , (proxy, method, args) -> {
          // logger.info("proxy:{}", proxy);
          logger.info("method:{}", method);
          logger.info("args:{}", args);

          return null;
        });

    myAction.sayBye();
  }

  /**
   * 针对class
   */
  private static void forClazz() {
    var action = new JdkProxyImpl();
    var actionClassLoader = action.getClass().getClassLoader();
    var classArray = new Class[]{Action.class};

    var proxyInstance = (Action) Proxy.newProxyInstance(actionClassLoader, classArray, (proxy, method, args1) -> {
      System.out.println("proxy = " + proxy.getClass().getName());
      return method.invoke(action, args1);
    });

    proxyInstance.sayHello();
    proxyInstance.sayBye();
  }
}
