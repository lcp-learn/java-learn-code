package com.lcp.learn.java.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-09:42
 */
public class CGLibProxyFactory<T> {

  private T sourceObject;

  public CGLibProxyFactory(T sourceObject) {
    this.sourceObject = sourceObject;
  }

  public T getProxyInstance() {

    var enhancer = new Enhancer();//工具类

    enhancer.setSuperclass(sourceObject.getClass());//设置父类
    enhancer.setCallback((MethodInterceptor) (object, method, args1, methodProxy) -> method.invoke(sourceObject,
        args1));//设置回调函数

    return (T) enhancer.create();

  }
}
