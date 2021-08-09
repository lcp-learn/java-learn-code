package org.lcp.java.example.concurrent.threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2018/10/8-15:17
 */
public class ConcurrentMain {

  public static void main(String[] args) throws InterruptedException {

    AtomicInteger atomicInteger = new AtomicInteger();
    int value;
    value = atomicInteger.getAndAdd(3);
    System.out.println("value = " + value);

    ThreadLocalRandom.current();
    TimeUnit.SECONDS.sleep(3);

  }
}
