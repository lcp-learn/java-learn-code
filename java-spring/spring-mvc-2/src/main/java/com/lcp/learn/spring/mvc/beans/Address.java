package com.lcp.learn.spring.mvc.beans;

import com.google.common.base.Objects;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/7-18:00
 */
public class Address {

  private String name;
  private String email;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Address)) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equal(name, address.name) && Objects
        .equal(email, address.email);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, email);
  }

  public String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public final void setEmail(String email) {
    this.email = email;
  }
}
