package org.lcp.example.netty.basic.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.Arrays;
import java.util.List;
import org.lcp.example.netty.basic.beans.Protocol;
import org.lcp.example.netty.basic.beans.Request;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:28
 */
public class ByteToRequestDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

    //////////////////////////////////////////

    System.out.println("ByteToRequestDecoder = ");

    int length = in.readableBytes();

    byte[] body = new byte[length];

    in.readBytes(body);

    int protocolNumner = body[0];
    byte[] haha = Arrays.copyOfRange(body, 1, length);

    Protocol protocol = Protocol.getProtocolByNumber(protocolNumner);
    String value = new String(haha);

    Request request = new Request(protocol, value);
    out.add(request);

  }
}
