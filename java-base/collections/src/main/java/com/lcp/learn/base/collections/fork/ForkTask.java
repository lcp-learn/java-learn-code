package com.lcp.learn.base.collections.fork;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkTask extends RecursiveTask<Integer> {

  private static final long serialVersionUID = -6938249551288803884L;
  private List<Integer> target;

  public ForkTask(List<Integer> target) {
    this.target = target;
  }

  @Override
  protected Integer compute() {

    System.out.println("thread = " + Thread.currentThread().getName());

    int currentLength = target.size();
    if (currentLength > 20) {
      int index = currentLength / 2;

      var a = new ForkTask(target.subList(0, index));
      var b = new ForkTask(target.subList(index, currentLength));
      a.fork();
      b.fork();
      return a.join() + b.join();
    }
    return target.stream().mapToInt(Integer::intValue).sum();

  }
}
