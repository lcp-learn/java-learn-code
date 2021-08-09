package org.lcp.example.netty.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/9-17:33
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

    logger.info("msg:{}", msg.trim());
  }
}
