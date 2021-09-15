package com.lcp.learn.dubbo.consumer;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.User;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/3-19:14
 */
public class DubboAsyncConsumer {

  private static final Logger logger = LoggerFactory.getLogger(DubboAsyncConsumer.class);

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ReferenceConfig helloApiReferenceConfig = new ReferenceConfig<HelloApi>();
    helloApiReferenceConfig.setApplication(new ApplicationConfig("first-api-consumer"));
    helloApiReferenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:5181"));
    helloApiReferenceConfig.setInterface(HelloApi.class);
    helloApiReferenceConfig.setTimeout(5000);
    helloApiReferenceConfig.setVersion("0.0.2");
    helloApiReferenceConfig.setGroup("first-api-group");
    helloApiReferenceConfig.setProtocol("dubbo");
    helloApiReferenceConfig.setCache("lru");

    // helloApiReferenceConfig.setAsync(true);

    HelloApi helloApi = (HelloApi) helloApiReferenceConfig.get();

    RpcContext.getContext().setAttachment("company", "alibaba");
    User result = helloApi.hello("lcp", null);
    logger.info("result = " + result);

    // Future<User> futrue = RpcContext.getContext().getFuture();
    // System.out.println("futrue = " + futrue.get());

    RpcContext.getContext().getCompletableFuture().whenComplete((user, exception) -> {
      System.out.println("user class = " + user.getClass().getName());
      System.out.println("user = " + user);
      System.out.println("exception = " + exception);
    });

    CompletableFuture<User> result2 = helloApi.helloAsync("haha", 99);
    result2.whenComplete((user, exception) -> System.out.println("user async = " + user));


  }
}
