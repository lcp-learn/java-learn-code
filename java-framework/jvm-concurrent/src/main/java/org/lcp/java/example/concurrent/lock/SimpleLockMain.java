package org.lcp.java.example.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/11-17:09
 */
public class SimpleLockMain {

  private static final Logger logger = LoggerFactory.getLogger(SimpleLockMain.class);

  public static void main(String[] args) throws Exception {

    var counter = new Counter(0);

    var pool = Executors.newFixedThreadPool(3);

    var lock = new ReentrantLock(false);

    var taskCount = 5;
    var countDownLatch = new CountDownLatch(taskCount);

    for (int i = 0; i < taskCount; i++) {
      pool.execute(new AddAction(counter, lock, countDownLatch));
    }
    countDownLatch.await();
    logger.info("final value:{}", counter.getValue());

    pool.shutdown();

    Runtime.version().version().forEach(version -> System.out.println("version = " + version));

    System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());

  }
}
