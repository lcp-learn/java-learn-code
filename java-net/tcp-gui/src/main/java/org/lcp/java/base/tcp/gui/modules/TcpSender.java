package org.lcp.java.base.tcp.gui.modules;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-10:09
 */
public class TcpSender {

  private Channel channel;

  public void send(String ip, int port) throws Exception {

    final EventLoopGroup group = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group).channel(NioSocketChannel.class)  // 使用NioSocketChannel来作为连接用的channel类
        .option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
          @Override
          public void initChannel(SocketChannel ch) throws Exception {
            System.out.println("正在连接中...");
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new ClientHandler()); //客户端处理类

          }
        });

    //发起异步连接请求，绑定连接端口和host信息
    SocketAddress remoteAddress = new InetSocketAddress(ip, port);
    SocketAddress localAddress = new InetSocketAddress(8762);
    final ChannelFuture channelFuture = bootstrap.connect(remoteAddress, localAddress).sync();

    channelFuture.addListener((ChannelFutureListener) arg0 -> {
      if (channelFuture.isSuccess()) {
        System.out.println("连接服务器成功");

      } else {
        System.out.println("连接服务器失败");
        channelFuture.cause().printStackTrace();
        group.shutdownGracefully(); //关闭线程组
      }
    });

    // 发送客户端的请求
    channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hahahah".getBytes(Charset.defaultCharset())));

    this.channel = channelFuture.channel();

  }

  public Channel getChannel() {
    return channel;
  }

}
