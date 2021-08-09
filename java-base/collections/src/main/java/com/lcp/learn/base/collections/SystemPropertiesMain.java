package com.lcp.learn.base.collections;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/8/12-14:53
 */
public class SystemPropertiesMain {

  private static final Logger logger = LoggerFactory.getLogger(SystemPropertiesMain.class);

  public static void main(String[] args) {

    var properties = System.getProperties();
    properties.forEach((k, v) -> logger.info("{}:\t{}", k, v));

    System.out.println(" =================== ");

    System.getenv().forEach((k, v) -> logger.info("{}:\t{}", k, v));

    System.out.println(" =================== ");

    String path = System.getenv("PATH");
    String[] pathArray = path.split(":");
    Arrays.stream(pathArray).forEach(item -> logger.info("item = " + item));

  }
}
