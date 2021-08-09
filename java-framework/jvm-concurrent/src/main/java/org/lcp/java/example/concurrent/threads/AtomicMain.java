package org.lcp.java.example.concurrent.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/10-15:11
 */
public class AtomicMain {

  public static void main(String[] args) {

    var i = new AtomicInteger();
    i.incrementAndGet();
    i.addAndGet(234);
  }
}
