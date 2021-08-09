package org.lcp.example.netty.basic.logichandler;

import org.lcp.example.netty.basic.beans.Request;
import org.lcp.example.netty.basic.beans.Response;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-14:26
 */
public class AHandler implements LogicHandler {

  /**
   * 处理
   *
   * @param request
   * @return
   */
  @Override
  public Response handle(Request request) {
    return new Response(0, "AA");
  }
}
