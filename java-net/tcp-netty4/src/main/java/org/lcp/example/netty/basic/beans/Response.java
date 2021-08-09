package org.lcp.example.netty.basic.beans;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-14:25
 */
public class Response {

  int code;
  String result;

  public Response() {
  }

  public Response(int code, String result) {
    this.code = code;
    this.result = result;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
