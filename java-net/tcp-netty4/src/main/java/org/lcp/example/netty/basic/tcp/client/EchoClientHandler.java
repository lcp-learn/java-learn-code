package org.lcp.example.netty.basic.tcp.client;

import static io.netty.channel.ChannelHandler.Sharable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lcp.example.netty.basic.beans.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/1-13:24
 */
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<Response> {

  private final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);

  private String data;

  public EchoClientHandler() {

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < Integer.MAX_VALUE / 100000099; i++) {
      stringBuilder.append(i);
    }

    data = stringBuilder.toString();
  }

  @Override
  public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    System.out.println("channelActive = ");

    // Request request = new Request(A, "hahaha");

    channelHandlerContext.writeAndFlush("requestrequestrequest");
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
    logger.info("client receive: {} , {}", response.getCode(), response.getResult());
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.close();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
    cause.printStackTrace();
    channelHandlerContext.close();
  }
}
