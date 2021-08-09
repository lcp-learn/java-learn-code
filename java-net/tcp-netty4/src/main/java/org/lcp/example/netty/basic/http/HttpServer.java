package org.lcp.example.netty.basic.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/5-16:33<br/>
 */
public class HttpServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = new HttpServer();
    System.out.println("Http Server listening on 8844 ...");
    server.start();
  }

  private void start() throws Exception {
    //        EventLoopGroup bossGroup = new EpollEventLoopGroup(8);
    //        EventLoopGroup workerGroup = new EpollEventLoopGroup(16);

    EventLoopGroup bossGroup = new NioEventLoopGroup(8);
    EventLoopGroup workerGroup = new NioEventLoopGroup(16);
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup, workerGroup)
          //                    .channel(EpollServerSocketChannel.class)
          .channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128)
          .childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) {
              socketChannel.pipeline().addLast(new HttpResponseEncoder()); // server端发送的是httpResponse
              // ，所以要使用HttpResponseEncoder进行编码
              socketChannel.pipeline().addLast(new HttpRequestDecoder());// server端接收到的是httpRequest
              // ，所以要使用HttpRequestDecoder进行解码
              //                            socketChannel.pipeline().addLast(new HttpObjectAggregator(Integer
              //                            .MAX_VALUE));//要让netty以整体接收的话，在pipeline中添加HttpObjectAggregator

              socketChannel.pipeline().addLast(new HttpServerInboundHandler());
            }
          });

      ChannelFuture channelFuture = serverBootstrap.bind(8844).sync();

      channelFuture.channel().closeFuture().sync();

    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

}
