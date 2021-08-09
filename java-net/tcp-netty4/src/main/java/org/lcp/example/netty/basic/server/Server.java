package org.lcp.example.netty.basic.server;

import io.netty.channel.Channel;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/12/25-14:25
 */
public interface Server {

  /**
   * 启动
   */
  void start(int port) throws Exception;

  /**
   * 停止
   */
  void stop() throws Exception;

  /**
   * 得到channel
   *
   * @return
   */
  Channel getChannel();

}
