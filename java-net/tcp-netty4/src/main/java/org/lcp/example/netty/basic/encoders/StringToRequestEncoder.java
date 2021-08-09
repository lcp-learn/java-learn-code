package org.lcp.example.netty.basic.encoders;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import org.lcp.example.netty.basic.beans.Protocol;
import org.lcp.example.netty.basic.beans.Request;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-08-13:30
 */
public class StringToRequestEncoder extends MessageToMessageEncoder<String> {

  // @Override
  // protected void encode(ChannelHandlerContext channelHandlerContext,
  //                       Request request,
  //                       ByteBuf byteBuf) throws Exception {
  //
  //     int protocolNumber = request.getProtocol().getProtocolNumber();
  //     String body = request.getBody();
  //
  //     byteBuf.writeByte(protocolNumber);
  //     byteBuf.writeBytes(body.getBytes());
  //
  // }

  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
    System.out.println("StringToRequestEncoder = ");
    Request request = new Request();
    request.setProtocol(Protocol.B);
    request.setBody(msg);
    ctx.channel().writeAndFlush(request);
  }
}
