package com.lcp.learn.spring.spb.simple.exceptions;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-14:53
 */
public class ParamException extends Exception {

  private static final long serialVersionUID = 4923664066681184109L;

  private final CheckError checkError;

  public ParamException(CheckError checkError, String message) {
    super(message);
    this.checkError = checkError;
  }

  public ParamException(CheckError checkError) {
    this(checkError, checkError.getDesc());

  }

  public CheckError getCheckError() {
    return checkError;
  }

}
