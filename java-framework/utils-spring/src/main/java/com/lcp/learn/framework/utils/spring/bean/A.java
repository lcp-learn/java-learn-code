package com.lcp.learn.framework.utils.spring.bean;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/3/31-11:54
 */
public class A {

  private String name;

  public A(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }
}
