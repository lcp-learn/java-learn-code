package com.lcp.learn.dubbo.provider.rest;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-12:26
 */
@DubboService
@Component("helloApiHttpImpl")
@Path("/provider/restful/hello")
public class HelloApiHttpImpl implements HelloApi {

  private final Logger logger = LoggerFactory.getLogger(HelloApiHttpImpl.class);

  private ExecutorService threadPool;

  public HelloApiHttpImpl() {
    threadPool = Executors.newFixedThreadPool(10);
  }

  @Override
  @POST
  @GET
  @Path("/world")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
  @Produces(MediaType.APPLICATION_JSON)
  public User hello(@QueryParam("name") String name, @QueryParam("sex") Sex sex) {
    User user = new User("hahaha_" + name, RandomUtils.nextInt(0, 99));
    logger.info("sex:{},result={}", sex, JSON.toJSONString(user));
    return user;
  }

  @Override
  public CompletableFuture<User> helloAsync(String name, int age) {
    // RpcContext rpcContext = RpcContext.getContext();
    return CompletableFuture.supplyAsync(() -> new User(name, age), threadPool);
  }

}
