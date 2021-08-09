package com.lcp.learn.spring.classloader.api;

import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/20-12:52
 */
@Service
public class UserApiImpl implements UserApi {

  @Override
  public String hello(String name) {
    System.out.println("name = " + name);
    return "hello," + name;
  }
}
