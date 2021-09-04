package com.lcp.learn.dubbo.filter;

import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/10-13:53
 */
public class MyFilter implements Filter {

  private final Logger logger = LoggerFactory.getLogger(MyFilter.class);

  /**
   * Make sure call invoker.invoke() in your implementation.
   *
   * @param invoker
   * @param invocation
   */
  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

    RpcContext rpcContext = RpcContext.getContext();

    String remoteAddress = rpcContext.getRemoteAddressString();
    logger.info("remote address:{}", remoteAddress);

    Result result = invoker.invoke(invocation);

    return result;
  }
}
