package com.lcp.learn.base.collections.fork;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import org.apache.commons.lang3.RandomUtils;

public class ForkJoinMain {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    var forkJoinPool = new ForkJoinPool(20);

    List<Integer> list = Lists.newLinkedList();
    var act = 0;
    for (var i = 0; i < 500; i++) {
      var random = RandomUtils.nextInt(1, 99);
      act += random;
      list.add(random);
    }
    System.out.println("list = " + list);

    Future<Integer> result = forkJoinPool.submit(new ForkTask(list));

    int count = result.get();
    System.out.println("count = " + count);
    System.out.println("act = " + act);

  }
}
