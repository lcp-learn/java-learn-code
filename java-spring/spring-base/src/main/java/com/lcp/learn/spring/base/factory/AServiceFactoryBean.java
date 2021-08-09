package com.lcp.learn.spring.base.factory;

//import com.lcp.learn.spring.base.service.AService;
//import com.lcp.learn.spring.base.service.impl.AServiceImpl;
//import org.springframework.beans.factory.FactoryBean;

import com.lcp.learn.spring.base.service.AService;
import com.lcp.learn.spring.base.service.impl.AServiceImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-16:18
 */
public class AServiceFactoryBean implements InitializingBean, FactoryBean<AService> {

  private int id;

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public AService getObject() throws Exception {
    if (id == 1) {
      return new AServiceImpl();
    }
    return null;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public Class<? extends AService> getObjectType() {
    return null;
  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
