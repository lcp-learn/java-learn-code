package com.lcp.learn.base.jar.canrun;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/6-17:39
 */
public class JarRunMain {

  private static final Logger logger = LoggerFactory.getLogger(JarRunMain.class);

  public static void main(String[] args) {

    logger.info("{}", RandomStringUtils.random(32, true, true));
    var sss = """
        asdasdasdasdasdasd
        asdasdasdasdasdasd
        asdasdasdasdasdasd
        asdasdasdasdasdasd
        """;

    logger.info("sss:{}", sss);
  }
}
