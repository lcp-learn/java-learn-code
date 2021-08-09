package com.lcp.learn.spring.base.processors;

import com.lcp.learn.spring.base.service.AService;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/4-12:20
 */
@Component
public class MySecondBeanPostProcessor implements BeanPostProcessor {

  private final Logger logger = LoggerFactory.getLogger(MySecondBeanPostProcessor.class);

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof AService) {
      logger.info("bean 222222 对象初始化之前.beanName:{},bean:{}", beanName, bean.getClass().getName());
    }
    return bean;
    // return bean对象监控代理对象
  }

  @Override
  public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
    // 为当前 bean 对象注册监控代理对象，负责增强 bean 对象方法的能力
    Class beanClass = bean.getClass();
    if (bean instanceof AService) {

      return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
          (proxy1, method, args) -> {
            logger.info("AService 22222 中的 doSome() 被拦截了···bean:{}", bean.getClass().getName());
            Object result = method.invoke(bean, args);
            logger.info("result 222 :{}", (String) result);
            return (String) result + "->p2";
            // var result = (String) method.invoke(bean, args);
            // return result.toUpperCase();
          });
    }
    return bean;
  }
}
