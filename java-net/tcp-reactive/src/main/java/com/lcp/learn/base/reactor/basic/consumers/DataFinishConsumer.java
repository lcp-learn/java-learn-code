package com.lcp.learn.base.reactor.basic.consumers;

import java.util.function.Consumer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/25-16:55
 */
public class DataFinishConsumer implements Consumer<String> {

  @Override
  public void accept(String s) {
    System.out.println("s = " + s);
  }
}
