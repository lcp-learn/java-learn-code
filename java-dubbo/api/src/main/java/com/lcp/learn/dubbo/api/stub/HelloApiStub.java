package com.lcp.learn.dubbo.api.stub;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/8-18:59
 */
public class HelloApiStub implements HelloApi {

  private final Logger logger = LoggerFactory.getLogger(HelloApiStub.class);

  private final HelloApi helloApi;

  public HelloApiStub(HelloApi helloApi) {
    this.helloApi = helloApi;
  }

  @Override
  public User hello(String name, Sex sex) {

    if (name == null) {
      logger.error("名字 is null");
      return new User();
    }
    logger.info("名字可用");

    return helloApi.hello(name, sex);
  }

  @Override
  public CompletableFuture<User> helloAsync(String name, int age) {
    return helloApi.helloAsync(name, age);
  }

}
