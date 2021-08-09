package org.lcp.example.netty.basic.tcp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import org.lcp.example.netty.basic.decoders.ByteToResponseDecoder;
import org.lcp.example.netty.basic.encoders.RequestToByteEncoder;
import org.lcp.example.netty.basic.encoders.StringToRequestEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/8-11:51<br/>
 */
public class EchoClient {

  private final InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9987);
  private final Logger logger = LoggerFactory.getLogger(EchoClient.class);

  public static void main(String[] args) throws Exception {
    new EchoClient().launch();
  }

  private void launch() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup(10);
    EventLoopGroup workerGroup = new NioEventLoopGroup(10);

    try {
      Bootstrap serverBootstrap = new Bootstrap();
      serverBootstrap.group(workerGroup).channel(NioSocketChannel.class).remoteAddress(inetSocketAddress)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) {
              socketChannel.pipeline().addLast(new StringToRequestEncoder());
              socketChannel.pipeline().addLast(new RequestToByteEncoder());
              socketChannel.pipeline().addLast(new ByteToResponseDecoder());
              socketChannel.pipeline().addLast(new EchoClientHandler());

            }
          });

      // Bind and start to accept incoming connections.
      ChannelFuture channelFuture = serverBootstrap.connect().sync();
      channelFuture.channel().closeFuture().sync();

    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

}
