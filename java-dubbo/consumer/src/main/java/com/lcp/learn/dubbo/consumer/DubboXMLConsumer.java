package com.lcp.learn.dubbo.consumer;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.lcp.learn.dubbo.beans.Sex.M;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/3-19:14
 */
public class DubboXMLConsumer {

  private static final Logger logger = LoggerFactory.getLogger(DubboXMLConsumer.class);

  public static void main(String[] args) throws InterruptedException {

    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-consumer-beans.xml");
    applicationContext.start();
    HelloApi helloApi = applicationContext.getBean(HelloApi.class);

    // var name = "lcp_" + RandomStringUtils.random(10, true, false);
    String name = "lcp_";
    // String name = null;
    Sex sex = M;
    User result = helloApi.hello(name, sex);
    logger.info("result = " + result);
  }
}
