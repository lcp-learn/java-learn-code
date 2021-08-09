package org.lcp.java.example.concurrent.threads.runnables;

import java.util.concurrent.locks.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2018/10/8-15:19
 */
public class MyThreadA implements Runnable {

  private final Logger logger = LoggerFactory.getLogger(MyThreadA.class);

  private Lock lock;
  private StringBuilder value;

  public MyThreadA(Lock lock, StringBuilder value) {
    this.lock = lock;
    this.value = value;
  }

  @Override
  public void run() {

    lock.tryLock();
    value.append(Thread.currentThread().getName()).append("==");
    logger.info("value = " + value.toString());
    lock.unlock();
  }
}
