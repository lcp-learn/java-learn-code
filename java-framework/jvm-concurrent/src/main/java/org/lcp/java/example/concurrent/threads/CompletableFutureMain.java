package org.lcp.java.example.concurrent.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/26-18:36
 */
public class CompletableFutureMain {

  private static final Logger logger = LoggerFactory.getLogger(CompletableFutureMain.class);

  public static void main(String[] args) {

    ExecutorService pool = Executors.newFixedThreadPool(5);

    IntStream.rangeClosed(1, 3).boxed().forEach(item -> {
      try {
        CompletableFuture.supplyAsync(() -> {

          var data = RandomStringUtils.random(5, true, true);
          logger.info("number:{}.save to db:{}.thread:{}", item, data, Thread.currentThread().getName());

          sleep();

          return data;

        }, pool).thenApplyAsync(dataFromDB -> {

          logger.info("number:{}.get db data:{}.thread:{}", item, dataFromDB, Thread.currentThread().getName());

          sleep();

          logger.info("number:{}.save to redis.thread:{}", item, Thread.currentThread().getName());
          return dataFromDB;

        }, pool).whenCompleteAsync((dataFromDB, throwable) -> logger.info("number:{}.all saved:{} .thread:{}",
            item, dataFromDB, Thread.currentThread().getName()), pool);

      } catch (Exception exception) {
        exception.printStackTrace();
      }
    });
  }

  private static void sleep() {
    try {
      Thread.sleep(RandomUtils.nextInt(500, 10_000));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
