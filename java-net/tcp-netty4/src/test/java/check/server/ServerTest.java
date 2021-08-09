package check.server;

import org.lcp.example.netty.basic.server.Server;
import org.lcp.example.netty.basic.server.netty.NettyServerImpl;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-14:27
 */
public class ServerTest {

  //    @SneakyThrows
  //    @Test
  //    public void test12() {
  //
  //        Server server = new NettyServerImpl();
  //        server.start(9865);
  //    }

  public static void main(String[] args) {
    Server server = new NettyServerImpl();
    try {
      server.start(9987);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
