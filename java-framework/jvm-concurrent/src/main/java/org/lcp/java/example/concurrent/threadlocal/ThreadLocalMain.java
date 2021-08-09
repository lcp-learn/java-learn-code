package org.lcp.java.example.concurrent.threadlocal;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-13:36
 */
public class ThreadLocalMain {

  private static final Logger logger = LoggerFactory.getLogger(ThreadLocalMain.class);

  private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
      new LinkedBlockingQueue<>(10), new ThreadFactory() {

    private final AtomicInteger poolNumber = new AtomicInteger(INTEGER_ONE);

    @Override
    public Thread newThread(Runnable runnable) {
      return new Thread(runnable, "lcp-thread-" + poolNumber.incrementAndGet());
    }
  });

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 100_0000; ++i) {
      poolExecutor.execute(() -> {
        ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();
        var bigObj = new BigObject();
        threadLocal.set(bigObj);
        // 其他业务代码
        logger.info("bigObj:{}", bigObj.hashCode());
      });
      Thread.sleep(1000);

    }

  }

  static class BigObject {

    // 100M
    private byte[] bytes = new byte[100 * 1024 * 1024];
  }

}
