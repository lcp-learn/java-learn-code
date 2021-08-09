package org.lcp.example.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;
import org.lcp.example.netty.chat.client.handler.GroupChatClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/9-17:32
 */
public class GroupChatClient {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final String host;
  private final int port;

  public GroupChatClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
    new GroupChatClient("127.0.0.1", 7000).run();
  }

  public void run() throws Exception {
    var group = new NioEventLoopGroup();
    try {
      var bootstrap =
          new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              //得到pipeline
              ChannelPipeline pipeline = ch.pipeline();

              //加入相关handler
              pipeline.addLast("decoder", new StringDecoder());
              pipeline.addLast("encoder", new StringEncoder());

              //加入自定义的handler
              pipeline.addLast(new GroupChatClientHandler());

            }
          });
      var channelFuture = bootstrap.connect(host, port).sync();

      //得到channel
      var channel = channelFuture.channel();
      logger.info("-------{}-------", channel.localAddress());

      //客户端需要输入信息，创建一个扫描器
      var scanner = new Scanner(System.in);
      while (scanner.hasNextLine()) {
        String msg = scanner.nextLine();
        //通过channel 发送到服务器端
        channel.writeAndFlush(msg + "\r\n");
      }

    } finally {
      group.shutdownGracefully();
    }
  }

}
