package org.lcp.example.netty.basic.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import org.lcp.example.netty.basic.decoders.ByteToHahaDecoder;
import org.lcp.example.netty.basic.decoders.HahaToRequestDecoder;
import org.lcp.example.netty.basic.encoders.ResponseToByteEncoder;
import org.lcp.example.netty.basic.tcp.server.in.EchoServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/8-11:51<br/>
 */
public class EchoServer {

  private final Logger logger = LoggerFactory.getLogger(EchoServer.class);

  public static void main(String[] args) throws Exception {
    new EchoServer().launch();
  }

  private void launch() throws Exception {
    var bossGroup = new NioEventLoopGroup(10);
    var workerGroup = new NioEventLoopGroup(10);

    var eventExecutorGroup = new DefaultEventExecutorGroup(10);

    var threadLocal = new ThreadLocal<String>();

    var serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        // .handler()
        // .option(ChannelOption.SO_BACKLOG, 128)
        // .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          public void initChannel(SocketChannel socketChannel) {

            ChannelPipeline channelPipeline = socketChannel.pipeline();
            channelPipeline.addLast(
                new ByteToHahaDecoder(),
                new HahaToRequestDecoder(),
                new ResponseToByteEncoder(),
                new EchoServerHandler(threadLocal)
            );
          }
        });

    // Bind and start to accept incoming connections.
    var channelFuture = serverBootstrap.bind(9987).sync();

    channelFuture.channel().closeFuture().addListener((ChannelFutureListener) future -> {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    });

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("args = 1");
      System.out.println("args = 2");
    }));

  }
}
