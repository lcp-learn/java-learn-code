package com.lcp.learn.spring.spb.simple.beans.response;

import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-14:48
 */
public class ResponseInfo<T> implements Serializable {

  private static final long serialVersionUID = 6058778936032525143L;

  private int code;
  private String desc;
  private T data;

  public ResponseInfo() {
  }

  public ResponseInfo(int code, String desc, T data) {
    this.code = code;
    this.desc = desc;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
