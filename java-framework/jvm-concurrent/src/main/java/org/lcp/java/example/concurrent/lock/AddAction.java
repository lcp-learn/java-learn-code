package org.lcp.java.example.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/11-17:13
 */
public class AddAction implements Runnable {

  private final Logger logger = LoggerFactory.getLogger(AddAction.class);
  private final Lock lock;
  private final CountDownLatch countDownLatch;
  private Counter counter;

  public AddAction(Counter counter, Lock lock, CountDownLatch countDownLatch) {
    this.counter = counter;
    this.lock = lock;
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    int ramdom = RandomUtils.nextInt(1, 10);
    try {
      lock.lock();
      counter.add(ramdom);
      logger.info("random:{}\tnewValue = {}\tcurrent thread name:{}", ramdom, counter.getValue(),
          Thread.currentThread().getName());
    } catch (Exception ignored) {

    } finally {
      lock.unlock();
    }

    countDownLatch.countDown();
  }
}
