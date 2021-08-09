package com.lcp.learn.base.collections.func;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/9-20:31
 */
public interface MyChecker {

  Logger logger = LoggerFactory.getLogger(MyChecker.class);

  boolean check(int a);

  default void app() {
    logger.info("true = {}", true);
  }
}
