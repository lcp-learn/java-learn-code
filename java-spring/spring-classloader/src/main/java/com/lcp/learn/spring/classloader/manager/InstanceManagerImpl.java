package com.lcp.learn.spring.classloader.manager;

import com.lcp.learn.spring.classloader.loader.ApiClassLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/20-12:49
 */
@Service
public class InstanceManagerImpl implements InstanceManager, ApplicationContextAware {

  private ApiClassLoader apiClassLoader = new ApiClassLoader();
  private ApplicationContext applicationContext;

  @Override
  public <T> T getInstance(Class apiClass) {

    return (T) applicationContext.getBean(apiClass);

  }

  @Override
  public <T> T getInstance(String apiClassName) throws ClassNotFoundException {
    Class apiClass = apiClassLoader.loadClass(apiClassName);
    return getInstance(apiClass);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
