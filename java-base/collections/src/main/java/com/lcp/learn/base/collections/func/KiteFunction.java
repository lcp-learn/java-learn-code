package com.lcp.learn.base.collections.func;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/2-18:05
 */
@FunctionalInterface
public interface KiteFunction<T, S, R> {

  /**
   * 定义一个双参数的方法
   *
   * @param t
   * @param s
   * @return
   */
  R kiteRun(T t, S s);
}
