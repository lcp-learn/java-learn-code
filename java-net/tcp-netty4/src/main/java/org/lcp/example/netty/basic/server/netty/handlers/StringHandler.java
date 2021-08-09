package org.lcp.example.netty.basic.server.netty.handlers;

import static io.netty.channel.ChannelHandler.Sharable;

import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-14:53
 */
@Sharable
public class StringHandler extends AbstractHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) {
    InetSocketAddress inetSocketAddress = (InetSocketAddress) channelHandlerContext.channel().remoteAddress();
    // logger.info("{}", inetSocketAddress.getClass().getName());

    logger.info("msg from client :{} .port:{}", msg, inetSocketAddress.getPort());
  }

}
