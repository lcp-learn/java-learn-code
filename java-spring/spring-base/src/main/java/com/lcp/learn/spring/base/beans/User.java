package com.lcp.learn.spring.base.beans;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/13-11:06
 */
public class User implements Serializable {

  private static final long serialVersionUID = -1631279775355577170L;

  private String name = EMPTY;
  private int age = INTEGER_ZERO;
  private String key = EMPTY;

  public User() {
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void init() {
    this.name = RandomStringUtils.random(10, true, false);
    this.age = RandomUtils.nextInt(0, 99);
    System.out.println("name = " + name + ",age=" + age);
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
    return age == user.age &&
        Objects.equals(name, user.name);
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

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
