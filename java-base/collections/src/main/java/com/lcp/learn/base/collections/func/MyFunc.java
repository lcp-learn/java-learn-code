package com.lcp.learn.base.collections.func;

import java.util.function.Function;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/12-11:33
 */
public class MyFunc implements Function<Integer, String> {

  @Override
  public String apply(Integer integer) {
    return "haha" + integer;
  }
}
