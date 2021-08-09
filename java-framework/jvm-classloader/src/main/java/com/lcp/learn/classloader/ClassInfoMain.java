package com.lcp.learn.classloader;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/11-10:51
 */
public class ClassInfoMain {

  private static final Logger logger = LoggerFactory.getLogger(ClassInfoMain.class);

  public static void main(String[] args) {

    var clazz = String.class;
    Arrays.stream(clazz.getDeclaredFields())
        .forEach(field -> logger.info("field:{},type:{}", field.getName(), field.getType().getName()));

  }

}
