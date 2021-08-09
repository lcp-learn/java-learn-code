package org.lcp.example.netty.basic.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.Arrays;
import java.util.List;
import org.lcp.example.netty.basic.beans.Protocol;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:28
 */
public class ByteToHahaDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

    //////////////////////////////////////////

    System.out.println("ByteToHahaDecoder = ");

    int length = in.readableBytes();

    byte[] body = new byte[length];

    in.readBytes(body);

    int protocolNumner = body[0];
    byte[] haha = Arrays.copyOfRange(body, 1, length);

    Protocol protocol = Protocol.getProtocolByNumber(protocolNumner);
    String value = new String(haha);

    String name = "hahaha";

    out.add(name);
    // channelHandlerContext.channel().writeAndFlush(name);

  }
}
