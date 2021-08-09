package org.lcp.java.base.tcp.gui.modules;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-11:15
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

  //处理服务端返回的数据
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String response) throws Exception {
    System.out.println("接受到server响应数据: " + response);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);

    //        String msg = "Are you ok?";
    //        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
    //        encoded.writeBytes(msg.getBytes());
    //        ctx.write(encoded);
    //        ctx.flush();

  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
