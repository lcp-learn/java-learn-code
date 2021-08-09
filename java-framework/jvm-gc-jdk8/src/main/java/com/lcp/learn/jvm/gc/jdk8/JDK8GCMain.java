package com.lcp.learn.jvm.gc.jdk8;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/11/3-12:11
 */
public class JDK8GCMain {

  private static final Logger logger = LoggerFactory.getLogger(JDK8GCMain.class);

  public static void main(String[] args) throws InterruptedException {

    List<Object> list = new LinkedList<>();

    IntStream.range(1, Integer.MAX_VALUE / 99).forEach(item -> list.add(new Object()));

    logger.info("finish = {}", list.size());
    new CountDownLatch(1).await();
  }
}
