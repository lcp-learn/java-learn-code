package org.lcp.example.socket.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/30-13:45
 */
public class SocketServer {

  private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

  private static void setProperties() throws Exception {

    String ip = InetAddress.getLocalHost().getHostAddress();
    String name = InetAddress.getLocalHost().getHostName();

    // System.getProperties().put("localip", ip);
    System.getProperties().put("localip", name);
  }

  public static void main(String[] args) throws Exception {

    setProperties();

    String GREETING = "Hello I must be going.\r\n";

    // TODO-lichunpeng 有待完善 2020/9/30-14:33

    int port = 7234; // default
    ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(port));
    serverSocketChannel.configureBlocking(false);
    while (true) {
      logger.info("Waiting for connections");
      SocketChannel socketChannel = serverSocketChannel.accept();
      if (socketChannel == null) {
        logger.info("null");
        Thread.sleep(2000);
      } else {
        logger.info("Incoming connection from: " + socketChannel.socket().getRemoteSocketAddress());
        buffer.rewind();
        socketChannel.write(buffer);
        socketChannel.close();
      }
    }
  }
}
