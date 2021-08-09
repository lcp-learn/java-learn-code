package com.lcp.learn.java.proxy.jdkproxy.impl;

import com.lcp.learn.java.proxy.api.Action;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-09:08
 */
public class JdkProxyImpl implements Action {

  @Override
  public void sayHello() {
    System.out.println("JdkProxyImpl sayHello");
  }

  @Override
  public void sayBye() {
    System.out.println("JdkProxyImpl sayBye");
  }
}
