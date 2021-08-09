package org.lcp.java.example.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/21-16:04
 */
public class ReentrantLockMain {

  private static final Logger logger = LoggerFactory.getLogger(ReentrantLockMain.class);

  public static void main(String[] args) throws Exception {

    // rwLock();
    // simpleLock();
    conditionLock();

  }

  private static void conditionLock() {

    var reentrantLock = new ReentrantLock();

    var readCondition = reentrantLock.newCondition();
    var writeCondition = reentrantLock.newCondition();

    var threadLocal = new ThreadLocal<String>();

    new Thread(() -> {
      try {
        reentrantLock.lock();
        threadLocal.set("t1 flag");
        readCondition.await();
        logger.info("{}", threadLocal.get());
      } catch (Exception exception) {
        exception.printStackTrace();
      } finally {
        reentrantLock.unlock();
      }

    }).start();

    new Thread(() -> {
      try {
        reentrantLock.lock();

        Thread.sleep(1000);
        threadLocal.set("t2 flag");
        readCondition.signal();
        logger.info("{}", threadLocal.get());
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      reentrantLock.unlock();
    }).start();
  }

  private static void simpleLock() {

    var reentrantLock = new ReentrantLock();

    final int value = 123;

    new Thread(() -> {
      try {
        reentrantLock.lock();
        System.out.println(System.currentTimeMillis() + "\tt1 = " + value);
        Thread.sleep(2000);
        reentrantLock.unlock();
      } catch (Exception exception) {
        exception.printStackTrace();
      }

    }).start();

    new Thread(() -> {
      try {
        reentrantLock.lock();
        System.out.println(System.currentTimeMillis() + "\tt2 = " + value);
        reentrantLock.unlock();
      } catch (Exception exception) {
        exception.printStackTrace();
      }

    }).start();

  }

  private static void rwLock() throws InterruptedException {

    final int[] value = {0};
    var count = 10;
    var countDown = new CountDownLatch(count);
    var pool = Executors.newFixedThreadPool(3);
    var reentrantReadWriteLock = new ReentrantReadWriteLock();
    var readLock = reentrantReadWriteLock.readLock();
    var writeLock = reentrantReadWriteLock.writeLock();

    for (int i = 0; i < count; i++) {
      pool.submit(() -> {

        if (RandomUtils.nextBoolean()) {  //read
          readLock.lock();
          logger.info("value read ={}", value[0]);
          readLock.unlock();
        } else { //write
          writeLock.lock();
          value[0]++;
          logger.info("value write ={}", value[0]);
          writeLock.unlock();
        }
        countDown.countDown();
      });
    }
    pool.shutdown();
    countDown.await();
    logger.info("final value:{}", value[0]);
  }
}
