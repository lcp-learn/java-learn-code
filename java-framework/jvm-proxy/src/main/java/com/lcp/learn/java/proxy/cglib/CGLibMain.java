package com.lcp.learn.java.proxy.cglib;

import com.lcp.learn.java.proxy.api.Action;
import com.lcp.learn.java.proxy.cglib.impl.CGlibActionImpl;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-09:34
 */
public class CGLibMain {

  public static void main(String[] args) {

    var action = new CGlibActionImpl();//原始
    var proxyFactory = new CGLibProxyFactory<Action>(action);// 工厂
    var proxyInstance = proxyFactory.getProxyInstance();//生成代理

    proxyInstance.sayBye();
  }
}
