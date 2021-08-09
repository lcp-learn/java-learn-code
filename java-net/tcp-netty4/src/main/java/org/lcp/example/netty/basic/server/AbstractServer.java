package org.lcp.example.netty.basic.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-14:26
 */
public abstract class AbstractServer implements Server {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
