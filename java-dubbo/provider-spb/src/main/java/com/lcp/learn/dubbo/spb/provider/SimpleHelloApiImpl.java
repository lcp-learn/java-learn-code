package com.lcp.learn.dubbo.spb.provider;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-13:10
 */
@DubboService
public class SimpleHelloApiImpl implements HelloApi {

  private final Logger logger = LoggerFactory.getLogger(SimpleHelloApiImpl.class);

  @Override
  @Cacheable(value = "getUserById", key = "#name")
  public User hello(String name, Sex sex) {
    logger.info("api impl name:{},sex:{}", name, sex);
    return new User(
        name + RandomStringUtils.random(10, true, true),
        RandomUtils.nextInt(11, 99));
  }

  @Override
  public CompletableFuture<User> helloAsync(String name, int age) {
    return CompletableFuture.supplyAsync(() ->
        new User(
            name + RandomStringUtils.random(10, true, true),
            RandomUtils.nextInt(11, 99))
    ).thenApply(user -> {
      logger.info("CompletableFuture name:{},age:{}", user.getName(), user.getAge());
      return user;
    });
  }

}
