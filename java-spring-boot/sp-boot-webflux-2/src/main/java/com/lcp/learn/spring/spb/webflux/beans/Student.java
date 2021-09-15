package com.lcp.learn.spring.spb.webflux.beans;

import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/7-14:06
 */
public class Student implements Serializable {

  private static final long serialVersionUID = -1241314706728539999L;
  private Integer id;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
