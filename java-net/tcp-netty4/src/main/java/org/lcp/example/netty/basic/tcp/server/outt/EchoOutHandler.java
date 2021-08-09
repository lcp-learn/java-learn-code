package org.lcp.example.netty.basic.tcp.server.outt;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import java.net.SocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/7/1-17:40
 */
public class EchoOutHandler extends ChannelOutboundHandlerAdapter {

  private final Logger logger = LoggerFactory.getLogger(EchoOutHandler.class);

  @Override
  public void connect(
      ChannelHandlerContext channelHandlerContext,
      SocketAddress remoteAddress,
      SocketAddress localAddress,
      ChannelPromise promise) throws Exception {

    logger.info("{}", remoteAddress);
    logger.info("{}", localAddress);
    logger.info("{}", promise);

    channelHandlerContext.write(Unpooled.EMPTY_BUFFER);

  }

  @Override
  public void bind(
      ChannelHandlerContext channelHandlerContext,
      SocketAddress localAddress,
      ChannelPromise promise) throws Exception {

    logger.info("{}", localAddress);
    logger.info("{}", promise);
  }

  @Override
  public void write(
      ChannelHandlerContext channelHandlerContext,
      Object msg,
      ChannelPromise channelPromise) throws Exception {

    // super.write(ctx, msg, promise);
    logger.info("{}", channelPromise);
    channelHandlerContext.write(msg, channelPromise);
    ReferenceCountUtil.release(msg);
    channelPromise.setSuccess();
  }

}
