package org.lcp.learn.java.beans;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/1/5-12:56
 */
public class Person extends User implements Serializable {

  private static final long serialVersionUID = 8569274118264228008L;
  protected String id;
  protected String simpleAddress;
  protected String email;

  public Person(String name, Integer age, String id, String simpleAddress, String email) {
    super(name, age);
    this.id = id;
    this.simpleAddress = simpleAddress;
    this.email = email;
  }

  public Person(String name, Integer age) {
    super(name, age);
  }

  public String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public String getSimpleAddress() {
    return simpleAddress;
  }

  public final void setSimpleAddress(String simpleAddress) {
    this.simpleAddress = simpleAddress;
  }

  public String getEmail() {
    return email;
  }

  public final void setEmail(String email) {
    this.email = email;
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
    Person rhs = (Person) obj;
    return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .append(this.id, rhs.id)
        .append(this.simpleAddress, rhs.simpleAddress)
        .append(this.email, rhs.email)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .appendSuper(super.hashCode())
        .append(id)
        .append(simpleAddress)
        .append(email)
        .toHashCode();
  }
}
