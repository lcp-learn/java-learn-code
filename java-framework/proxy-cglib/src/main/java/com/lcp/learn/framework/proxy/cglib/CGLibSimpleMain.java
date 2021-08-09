package com.lcp.learn.framework.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/3/8-17:00
 */
public class CGLibSimpleMain {

  private static final Logger logger = LoggerFactory.getLogger(CGLibSimpleMain.class);

  public static void main(String[] args) {

    Enhancer enhancer = getEnhancerInterface();
    // Enhancer enhancer = getEnhancerClass();

    MyApi sample = (MyApi) enhancer.create();
    var result = sample.hello("zhang3");

    logger.info("result {}", result);

  }

  private static Enhancer getEnhancerInterface() {
    Enhancer enhancer = new Enhancer();
    // enhancer.setSuperclass(MyApi.class);
    enhancer.setInterfaces(new Class[]{MyApi.class});
    enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
      System.out.println("before method run...");
      // Object result = proxy.invokeSuper(obj, args1);
      System.out.println("after method run...");
      return "aaa";
    });
    return enhancer;
  }

  private static Enhancer getEnhancerClass() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(MyApiImpl.class);
    enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
      System.out.println("before method run...");
      Object result = proxy.invokeSuper(obj, args1);
      System.out.println("after method run...");
      return result;
    });
    return enhancer;
  }
}
