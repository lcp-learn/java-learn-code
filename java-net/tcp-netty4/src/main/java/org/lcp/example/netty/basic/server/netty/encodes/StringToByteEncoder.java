package org.lcp.example.netty.basic.server.netty.encodes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:30
 */
public class StringToByteEncoder extends MessageToByteEncoder<String> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, String msg, ByteBuf byteBuf) {

    ByteBuf target = Unpooled.wrappedBuffer(msg.getBytes());
    byteBuf.writeBytes(target);

  }
}
