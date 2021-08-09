package com.lcp.learn.jvm.gc;

import java.util.LinkedList;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/27-09:59
 */
public class GCMain {

  public static void main(String[] args) throws InterruptedException {

    long i = Long.MIN_VALUE;

    var list = new LinkedList<>();

    while (i < Long.MAX_VALUE) {
      list.add(new Object());
      i++;
    }
    System.out.println("list = " + list);

    Thread.sleep(Long.MAX_VALUE);
  }
}
