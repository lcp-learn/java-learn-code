package com.lcp.learn.dubbo.consumer;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import static com.lcp.learn.dubbo.beans.Sex.M;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/3-19:14
 */
public class DubboConsumer {

  public static void main(String[] args) {

    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName("first-api-consumer");
    applicationConfig.setQosAcceptForeignIp(false);
    applicationConfig.setQosEnable(false);
    applicationConfig.setQosPort(23236);

    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setProtocol("zookeeper");
    registryConfig.setAddress("dev_zk:5181");
    registryConfig.setGroup("lcp-dubbo-xml3");//zk的路径

    DubboBootstrap bootstrap = DubboBootstrap.getInstance();
    bootstrap.application(applicationConfig).registry(registryConfig).protocol(new ProtocolConfig("dubbo")).start();

    ReferenceConfig<HelloApi> helloApiReference = new ReferenceConfig<HelloApi>();
    helloApiReference.setInterface(HelloApi.class);
    helloApiReference.setGroup("lcp-dubbo-xml");//逻辑的分组
    helloApiReference.setBootstrap(bootstrap);
    helloApiReference.setStub(true);

    HelloApi helloApi = helloApiReference.get();
    String name = "lcp_" + RandomStringUtils.random(10, true, false);
    // String name = null;
    Sex sex = M;
    User result = helloApi.hello(name, sex);
    System.out.println("result = " + result);
    result = helloApi.hello(name, sex);
    System.out.println("result = " + result);
    result = helloApi.hello(name, sex);
    System.out.println("result = " + result);
    result = helloApi.hello(name, sex);
    System.out.println("result = " + result);


  }
}
