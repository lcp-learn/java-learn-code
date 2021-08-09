package org.lcp.java.example.concurrent.lock.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/22-14:11
 */
public class MyAQS extends AbstractQueuedSynchronizer {

  private static final long serialVersionUID = 7577174339396932997L;

  public MyAQS() {
  }

  @Override
  protected boolean tryAcquire(int arg) {

    return super.tryAcquire(arg);
  }

  @Override
  protected boolean tryRelease(int arg) {
    return super.tryRelease(arg);
  }

  @Override
  protected boolean isHeldExclusively() {
    return getState() == 1;
  }
}
