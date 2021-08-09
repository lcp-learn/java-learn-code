package org.lcp.example.netty.basic.beans;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:33
 */
public enum Protocol {

  A(1), B(2);

  private int protocolNumber;

  Protocol(int protocolNumber) {
    this.protocolNumber = protocolNumber;
  }

  public static Protocol getProtocolByNumber(int protocolNumber) {
    for (Protocol protocol : Protocol.values()) {
      if (protocol.protocolNumber == protocolNumber) {
        return protocol;
      }
    }

    return null;
  }

  public int getProtocolNumber() {
    return protocolNumber;
  }
}
