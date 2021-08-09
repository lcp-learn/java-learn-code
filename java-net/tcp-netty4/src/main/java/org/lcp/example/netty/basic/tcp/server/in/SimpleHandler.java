package org.lcp.example.netty.basic.tcp.server.in;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/1-11:22
 */
public class SimpleHandler extends SimpleChannelInboundHandler<Object> {

  private final Logger logger = LoggerFactory.getLogger(SimpleHandler.class);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("msg class = " + msg.getClass());
    System.out.println(msg);
    ctx.writeAndFlush(Unpooled.copiedBuffer("received".getBytes(Charset.defaultCharset())));

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
