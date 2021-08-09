package org.lcp.learn.java.beans;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2018/7/3-14:16
 */
public class User implements Comparable<User>, Serializable, Cloneable {

  private static final long serialVersionUID = -1052131489490205094L;
  protected String name;
  protected Integer age;
  protected long score;
  private Address address;
  private Date checkoutTime;

  public User() {
  }

  public User(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public final void setAge(Integer age) {
    this.age = age;
  }

  public long getScore() {
    return score;
  }

  public final void setScore(long score) {
    this.score = score;
  }

  public Address getAddress() {
    return address;
  }

  public final void setAddress(Address address) {
    this.address = address;
  }

  public Date getCheckoutTime() {
    return checkoutTime;
  }

  public final void setCheckoutTime(Date checkoutTime) {
    this.checkoutTime = checkoutTime;
  }

  @Override
  public int compareTo(User targetUser) {
    return Integer.compare(targetUser.age, this.age);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    User rhs = (User) obj;
    return new EqualsBuilder()
        .append(this.name, rhs.name)
        .append(this.age, rhs.age)
        .append(this.score, rhs.score)
        .append(this.address, rhs.address)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(name)
        .append(age)
        .append(score)
        .append(address)
        .toHashCode();
  }

  @Override
  protected User clone() throws CloneNotSupportedException {
    return new User(new String(name), age);
  }

}
