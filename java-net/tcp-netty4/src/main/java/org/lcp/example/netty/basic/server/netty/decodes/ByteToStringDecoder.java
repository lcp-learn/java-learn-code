package org.lcp.example.netty.basic.server.netty.decodes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:28
 */
public class ByteToStringDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    int length = in.readableBytes();
    byte[] body = new byte[length];
    in.readBytes(body);
    String value = new String(body);
    out.add(value);

  }
}
