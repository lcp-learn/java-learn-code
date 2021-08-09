package com.lcp.learn.spring.classloader.manager;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/20-12:46
 */
public interface InstanceManager {

  <T> T getInstance(Class apiClass);

  <T> T getInstance(String apiClassName) throws ClassNotFoundException;
}
