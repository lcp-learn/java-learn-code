package com.lcp.learn.spring.mvc.beans;

import com.google.common.base.Objects;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/7-17:50
 */
public class User {

  private String name;
  private int age;
  //    private Date createDate;

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
    return age == user.age && Objects.equal(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, age);
  }

  public String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public final void setAge(int age) {
    this.age = age;
  }
}
