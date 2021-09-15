package com.lcp.learn.spring.spb.simple.beans.request;

import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-15:40
 */
public class RequestUser implements Serializable {

  private static final long serialVersionUID = -3561873494603312382L;

  @NotBlank(message = "name能为空")
  private String name;

  @NotNull(message = "age不能为空")
  @Digits(integer = 88, fraction = 0, message = "不是数字")
  @Max(value = 88, message = "age顶多88")
  @Min(value = 1, message = "age至少1")
  private Integer age;

  public RequestUser() {
  }

  public RequestUser(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
