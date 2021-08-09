package org.lcp.example.netty.basic.beans;

import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:33
 */
public class Request implements Serializable {

  private static final long serialVersionUID = 3984819630587724603L;
  Protocol protocol;
  String body;

  public Request() {
  }

  public Request(Protocol protocol, String body) {
    this.protocol = protocol;
    this.body = body;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

}
