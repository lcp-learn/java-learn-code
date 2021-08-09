package com.lcp.learn.base.collections;

import java.nio.ByteBuffer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/27-13:29
 */
public class ShutdownMain {

  public static void main(String[] args) {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("args = 1")));
    ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
  }
}
