package org.lcp.example.netty.basic.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.lcp.example.netty.basic.beans.Request;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:30
 */
public class RequestToByteEncoder extends MessageToByteEncoder<Request> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
    System.out.println("RequestToByteEncoder = ");
    int protocolNumber = request.getProtocol().getProtocolNumber();
    String body = request.getBody();

    byteBuf.writeByte(protocolNumber);
    byteBuf.writeBytes(body.getBytes());

  }
}
