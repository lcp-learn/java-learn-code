package com.lcp.learn.basic.zk;

import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/20-14:45
 */
public class ZKMain {

  private static final Logger logger = LoggerFactory.getLogger(ZKMain.class);
  private final static CountDownLatch countDownLatch = new CountDownLatch(1);
  final static String zookeeperAddress = "127.0.0.1:3181";

  public static void main(String[] args) throws Exception {

    var watcher = new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        if (event.getState() == KeeperState.SyncConnected) {

          logger.info("Watcher-eventType:{}", event.getType());

          if (event.getType() == Event.EventType.None) {

            countDownLatch.countDown();

          } else if (event.getType() == Event.EventType.NodeCreated) {

            logger.info("Watcher-listen:节点创建");

          } else if (event.getType() == Event.EventType.NodeChildrenChanged) {

            logger.info("Watcher-listen:子节点修改");

          }
        }
      }
    };

    ZooKeeper zookeeper = new ZooKeeper(zookeeperAddress, 5000, watcher);
    countDownLatch.await();

    var path = "/lcp";
    // var path2 = "/lcp2";

    //注册监听,每次都要重新注册，否则监听不到
    zookeeper.exists(path, watcher);

    // 创建节点
    try {
      String result = zookeeper.create(path, "一生一世".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      logger.info("创建:{}", result);
    } catch (Exception ex) {
      // ex.printStackTrace();
      logger.warn("已经创建了");
    }

    // 获取节点
    byte[] bs = zookeeper.getData(path, true, null);
    String result2 = new String(bs);
    logger.info("创建节点后的数据是:{}", result2);

    // 修改节点
    zookeeper.setData(path, "I love you2".getBytes(), -1);
    //
    bs = zookeeper.getData(path, true, null);
    String result3 = new String(bs);
    logger.info("修改节点后的数据是:{}", result3);

    // 删除节点
    zookeeper.delete(path, -1);
    logger.info("节点删除成功");

    Thread.sleep(10_000L);
  }

}

// @Override
// public void process(WatchedEvent event) {
//   logger.info("receive the event:" + event);
//   if (Event.KeeperState.SyncConnected == event.getState()) {
//     countDownLatch.countDown();
//   }
// }
// }
