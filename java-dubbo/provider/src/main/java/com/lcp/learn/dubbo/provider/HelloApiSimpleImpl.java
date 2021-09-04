package com.lcp.learn.dubbo.provider;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-12:26
 */
public class HelloApiSimpleImpl implements HelloApi {

  private final Logger logger = LoggerFactory.getLogger(HelloApiSimpleImpl.class);

  private ExecutorService threadPool;

  public HelloApiSimpleImpl() {
    threadPool = Executors.newFixedThreadPool(10);
  }

  @Override
  public User hello(String name, Sex sex) {
    User user = new User(name, RandomUtils.nextInt(0, 99));
    logger.info("result={}", JSON.toJSONString(user));
    return user;
  }

  @Override
  public CompletableFuture<User> helloAsync(String name, int age) {

    // RpcContext rpcContext = RpcContext.getContext();

    return CompletableFuture.supplyAsync(() -> new User(name, age), threadPool);
  }
}
