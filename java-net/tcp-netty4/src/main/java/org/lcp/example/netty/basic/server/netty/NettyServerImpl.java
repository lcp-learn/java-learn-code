package org.lcp.example.netty.basic.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.lcp.example.netty.basic.server.AbstractServer;
import org.lcp.example.netty.basic.server.netty.decodes.ByteToStringDecoder;
import org.lcp.example.netty.basic.server.netty.handlers.StringHandler;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-14:19
 */
public class NettyServerImpl extends AbstractServer {

  private Channel channel;

  private SimpleChannelInboundHandler<String> stringHandler = new StringHandler();
  //    private ByteToMessageDecoder byteToStringDecoder = new ByteToStringDecoder();

  /**
   * 启动
   *
   * @throws Exception
   */
  @Override
  public void start(int port) throws Exception {

    long startTime = System.currentTimeMillis();

    EventLoopGroup bossGroup = new NioEventLoopGroup(5);
    EventLoopGroup workerGroup = new NioEventLoopGroup(5);

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 128)
          // .childOption(ChannelOption.SO_KEEPALIVE, true)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) {
              ChannelPipeline channelPipeline = socketChannel.pipeline();
              channelPipeline.addLast(new ByteToStringDecoder());
              channelPipeline.addLast(stringHandler);
            }
          });

      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
      channel = channelFuture.channel();
      long endTime = System.currentTimeMillis();

      channelFuture.addListener((ChannelFutureListener) future -> {
        if (future.isDone()) {
          logger.info("server start done in {}ms , port:{}", (endTime - startTime), port);
        } else if (future.isSuccess()) {
          logger.info("success");
        }
      });

      channel.closeFuture().sync();

    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  @Override
  public Channel getChannel() {
    return channel;
  }

  @Override
  public void stop() throws Exception {
    // TODO-lichunpeng 待实现 2019/12/25-14:31
  }
}
