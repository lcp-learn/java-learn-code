package org.lcp.java.example.concurrent.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-10-16:43
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

  public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {

    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {

    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {

    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
      RejectedExecutionHandler rejectedExecutionHandler) {

    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedExecutionHandler);
  }
}
