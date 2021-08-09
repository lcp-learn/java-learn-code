package org.lcp.example.netty.basic.server.netty.handlers;

import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-15:09
 */
public abstract class AbstractHandler<T> extends SimpleChannelInboundHandler<T> {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
