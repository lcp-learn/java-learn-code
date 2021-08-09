package com.lcp.learn.framework.cached.redis.jedis;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/19-11:27
 */
public enum Switcher {

  USE_GATEWAY(1),
  USE_CHECK_F(2),
  USE_CHECK_S(3);

  private final int code;

  Switcher(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
