package org.lcp.example.netty.basic.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.lcp.example.netty.basic.tcp.server.in.SimpleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/8-11:51<br/>
 */
public class EchoServerSimple {

  private final Logger logger = LoggerFactory.getLogger(EchoServerSimple.class);

  public static void main(String[] args) throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup(10);
    EventLoopGroup workerGroup = new NioEventLoopGroup(10);

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 128)
          // .childOption(ChannelOption.SO_KEEPALIVE, true)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) {
              ChannelPipeline channelPipeline = socketChannel.pipeline();
              channelPipeline.addLast(new SimpleHandler());
            }
          });

      // Bind and start to accept incoming connections.
      ChannelFuture channelFuture = serverBootstrap.bind(9987).sync();

      channelFuture.channel().closeFuture().sync();

    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

}
