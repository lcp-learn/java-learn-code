package com.lcp.learn.dubbo.api;

import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import java.util.concurrent.CompletableFuture;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-12:22
 */
public interface HelloApi {

  User hello(String name, Sex sex);

  CompletableFuture<User> helloAsync(String name, int age);


}
