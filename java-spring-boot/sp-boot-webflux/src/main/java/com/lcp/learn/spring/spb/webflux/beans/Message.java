package com.lcp.learn.spring.spb.webflux.beans;


import com.google.common.base.Objects;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-11:38
 */
public class Message {

  private String body;
  private int age;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Message)) {
      return false;
    }
    Message message = (Message) o;
    return age == message.age && Objects.equal(body, message.body);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(body, age);
  }

  public String getBody() {
    return body;
  }

  public final void setBody(String body) {
    this.body = body;
  }

  public int getAge() {
    return age;
  }

  public final void setAge(int age) {
    this.age = age;
  }
}
