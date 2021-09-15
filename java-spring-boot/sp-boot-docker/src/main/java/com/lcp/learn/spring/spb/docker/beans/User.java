package com.lcp.learn.spring.spb.docker.beans;

import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/7-17:50
 */
public class User implements Serializable {

  private static final long serialVersionUID = -6841140045606192996L;

  private String name;
  private int age;

  public User() {
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
