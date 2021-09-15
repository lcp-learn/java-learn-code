package com.lcp.learn.dubbo.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-12:23
 */
public class User implements Serializable {

  private static final long serialVersionUID = -8685272197986272086L;
  private String name;
  private int age;

  public User() {
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return age == user.age && Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
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
