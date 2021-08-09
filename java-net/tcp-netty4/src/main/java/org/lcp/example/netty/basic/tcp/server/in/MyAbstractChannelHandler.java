package org.lcp.example.netty.basic.tcp.server.in;

import static org.lcp.example.netty.basic.beans.Protocol.A;

import io.netty.channel.SimpleChannelInboundHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import org.lcp.example.netty.basic.beans.Protocol;
import org.lcp.example.netty.basic.beans.Request;
import org.lcp.example.netty.basic.logichandler.AHandler;
import org.lcp.example.netty.basic.logichandler.LogicHandler;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/1-18:36
 */
public abstract class MyAbstractChannelHandler extends SimpleChannelInboundHandler<Request> {

  protected ThreadLocal<String> threadLocal;

  private Map<Protocol, LogicHandler> handlerMap;

  public MyAbstractChannelHandler() {
    handlerMap = new LinkedHashMap<>();
    handlerMap.put(A, new AHandler());

  }

  public MyAbstractChannelHandler(ThreadLocal<String> threadLocal) {
    this();
    this.threadLocal = threadLocal;
  }

  protected LogicHandler getHandler(Protocol protocol) {
    return handlerMap.get(protocol);
  }

}
