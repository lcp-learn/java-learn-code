package com.lcp.learn.frameworks.io.apache.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/6-14:13
 */
public class ApacheCommonIoMain {

  private static final Logger logger = LoggerFactory.getLogger(ApacheCommonIoMain.class);

  public static void main(String[] args) {

    char[] aa = new char[Integer.MAX_VALUE / 2];

    logger.info("aa:{}", aa.length);
  }
}
