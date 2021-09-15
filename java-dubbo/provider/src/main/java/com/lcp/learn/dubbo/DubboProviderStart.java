package com.lcp.learn.dubbo;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.config.DubboConfig;
import com.lcp.learn.dubbo.provider.HelloApiImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.spring.ServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-12:28
 */
public class DubboProviderStart {

  private static final Logger logger = LoggerFactory.getLogger(DubboProviderStart.class);

  public static void main(String[] args) throws Exception {

    // startWithBootstrap();
    // byAnnotationConfig();
    byXmlConfig();

  }

  /**
   * xml的方式
   *
   * @throws InterruptedException
   */
  private static void byXmlConfig() throws InterruptedException {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
    applicationContext.start();
    // new CountDownLatch(1).await();
    LockSupport.park();
  }

  /**
   * 注解的方式
   *
   * @throws InterruptedException
   */
  private static void byAnnotationConfig() throws InterruptedException {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
    Map<String, ServiceBean> serviceBean = applicationContext.getBeansOfType(ServiceBean.class);

    serviceBean.values().forEach(serviceBean1 -> {
      logger.info("{}", JSON.toJSONString(serviceBean1));
      serviceBean1.getRegistries().forEach(registryConfig -> logger.info("reg===>{}",
          registryConfig.getAddress()));
    });

    Map<String, HelloApi> map = applicationContext.getBeansOfType(HelloApi.class);
    // applicationContext.start();
    // map.get("helloApiImpl").hello(null, Sex.M);

    // new CountDownLatch(1).await();
    LockSupport.park();
  }

  /**
   * api的方式,--> 2.7.7
   *
   * @throws Exception
   */
  private static void startWithBootstrap() throws Exception {

    LinkedList serviceConfigList = new LinkedList<ServiceConfig>();

    ServiceConfig helloApiServiceConfig = new ServiceConfig<HelloApi>();
    helloApiServiceConfig.setInterface(HelloApi.class);
    helloApiServiceConfig.setRef(new HelloApiImpl());
    helloApiServiceConfig.setVersion("0.0.2xqxq");
    helloApiServiceConfig.setGroup("first-api-group");

    serviceConfigList.add(helloApiServiceConfig);

    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName("first-api-provider");
    applicationConfig.setQosAcceptForeignIp(false);
    applicationConfig.setQosEnable(false);
    applicationConfig.setQosPort(23235);

    DubboBootstrap bootstrap = DubboBootstrap.getInstance();
    bootstrap.application(applicationConfig).registry(new RegistryConfig("zookeeper://dev_zk:5181"))
        .protocol(new ProtocolConfig("dubbo")).services(serviceConfigList).start().await();
  }

}
