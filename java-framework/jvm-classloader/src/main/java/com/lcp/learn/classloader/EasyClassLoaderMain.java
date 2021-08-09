package com.lcp.learn.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-15:05
 */
public class EasyClassLoaderMain {

  private static final Logger logger = LoggerFactory.getLogger(EasyClassLoaderMain.class);

  public static void main(String[] args) {

    var p1 = EasyClassLoaderMain.class.getClassLoader();
    var p2 = p1.getParent();
    var p3 = p2.getParent();

    logger.info("p1:{}.name:{}", p1.getClass().getName(), p1.getName());
    logger.info("p2:{}.name:{}", p2.getClass().getName(), p2.getName());
    logger.info("p3:{}", p3.getClass().getName());

  }

}
