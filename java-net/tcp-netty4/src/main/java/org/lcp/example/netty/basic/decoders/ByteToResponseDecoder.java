package org.lcp.example.netty.basic.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.Arrays;
import java.util.List;
import org.lcp.example.netty.basic.beans.Response;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-15:00
 */
public class ByteToResponseDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

    int length = in.readableBytes();

    byte[] body = new byte[length];

    in.readBytes(body);

    int code = body[0];
    byte[] result = Arrays.copyOfRange(body, 1, length);

    Response response = new Response(code, new String(result));
    out.add(response);

  }
}
