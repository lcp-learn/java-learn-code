package com.lcp.learn.spring.spb.simple.exceptions;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-14:54
 */
public enum CheckError {

  PARAM_EMPTY(4001, "空"),
  TOO_BIG(4002, "忒大了");

  private final int code;
  private final String desc;

  CheckError(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public int getCode() {
    return code;
  }
}
