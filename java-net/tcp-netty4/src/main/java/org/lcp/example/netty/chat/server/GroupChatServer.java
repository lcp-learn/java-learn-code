package org.lcp.example.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.lcp.example.netty.chat.server.handler.GroupChatServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/9-17:28
 */
public class GroupChatServer {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final int port;

  public GroupChatServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
    new GroupChatServer(7000).run();
  }

  /**
   * 编写run方法，处理客户端的请求
   */
  public void run() throws Exception {

    //创建两个线程组
    var bossGroup = new NioEventLoopGroup(1);

    //8个NioEventLoop
    var workerGroup = new NioEventLoopGroup(8);

    var channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // try {
    var serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        // BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
        // 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
        .option(ChannelOption.SO_BACKLOG, 128)
        //是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）
        // 并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
        .childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) {

            //获取到pipeline
            var pipeline = ch.pipeline();

            //向pipeline加入解码器
            pipeline.addLast("decoder", new StringDecoder());

            //向pipeline加入编码器
            pipeline.addLast("encoder", new StringEncoder());

            //加入自己的业务处理handler
            pipeline.addLast(new GroupChatServerHandler(channelGroup));
          }
        });

    var channelFuture = serverBootstrap.bind(port).sync();

    //监听关闭
    channelFuture.channel().closeFuture().addListener((ChannelFutureListener) future -> {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    });

    // } finally {
    //     bossGroup.shutdownGracefully();
    //     workerGroup.shutdownGracefully();
    // }

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }));

    logger.info("netty 服务器启动");

  }

}
