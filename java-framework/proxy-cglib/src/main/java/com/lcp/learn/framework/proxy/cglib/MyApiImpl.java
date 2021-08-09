package com.lcp.learn.framework.proxy.cglib;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/3/8-17:06
 */
public class MyApiImpl implements MyApi {

  @Override
  public String hello(String name) {

    var value = "haha," + name;

    return value;
  }
}
