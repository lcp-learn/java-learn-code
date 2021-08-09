package org.lcp.example.netty.basic.logichandler;

import org.lcp.example.netty.basic.beans.Request;
import org.lcp.example.netty.basic.beans.Response;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-14:24
 */
public interface LogicHandler {

  /**
   * 处理
   *
   * @param request
   * @return
   */
  Response handle(Request request);
}
