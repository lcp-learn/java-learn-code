package org.lcp.example.netty.basic.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.lcp.example.netty.basic.beans.Response;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-14:56
 */
public class ResponseToByteEncoder extends MessageToByteEncoder<Response> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Response msg, ByteBuf out) throws Exception {

    out.writeByte(msg.getCode());
    out.writeBytes(msg.getResult().getBytes());
  }
}
