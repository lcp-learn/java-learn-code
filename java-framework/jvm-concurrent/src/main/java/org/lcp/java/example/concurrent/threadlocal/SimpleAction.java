package org.lcp.java.example.concurrent.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/2-17:02
 */
public class SimpleAction {

  private final Logger logger = LoggerFactory.getLogger(SimpleAction.class);

  private final ThreadLocal<String> strThreadLocal = new ThreadLocal<>();

  public void doAction(String name) {

    var newName = "logic-" + name;

    strThreadLocal.set(newName);
    logger.info("doAction====={}", newName);
    logger.info("get====={}", strThreadLocal.get());

    strThreadLocal.remove();

  }

}
