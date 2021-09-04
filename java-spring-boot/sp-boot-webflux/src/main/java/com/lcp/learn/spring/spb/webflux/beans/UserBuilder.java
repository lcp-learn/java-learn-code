package com.lcp.learn.spring.spb.webflux.beans;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/11/4-13:47
 */
public final class UserBuilder {

  private User user;

  private UserBuilder() {
    user = new User();
  }

  public static UserBuilder anUser() {
    return new UserBuilder();
  }

  public UserBuilder name(String name) {
    user.setName(name);
    return this;
  }

  public UserBuilder age(int age) {
    user.setAge(age);
    return this;
  }

  public User build() {
    return user;
  }
}
