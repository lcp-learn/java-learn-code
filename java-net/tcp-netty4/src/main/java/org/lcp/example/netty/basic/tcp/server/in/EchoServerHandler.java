package org.lcp.example.netty.basic.tcp.server.in;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.lcp.example.netty.basic.beans.Request;
import org.lcp.example.netty.basic.logichandler.LogicHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/1-11:22
 */
public class EchoServerHandler extends MyAbstractChannelHandler {

  private final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);
  private ExecutorService pool = Executors.newFixedThreadPool(10);

  public EchoServerHandler() {
  }

  public EchoServerHandler(ThreadLocal<String> threadLocal) {
    super(threadLocal);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) {

    System.out.println("channelRead0 = ");
    LogicHandler logicHandler = getHandler(request.getProtocol());

    CompletableFuture.supplyAsync(() -> logicHandler.handle(request), pool)//执行，得到结果
        .thenAccept(channelHandlerContext::writeAndFlush);// 结果写回去

  }

  @Override
  public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
    logger.info("server all received");
    channelHandlerContext.flush();
    // .addListener(ChannelFutureListener.CLOSE);
    channelHandlerContext.fireChannelReadComplete();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
    cause.printStackTrace();
    channelHandlerContext.close();
  }
}
