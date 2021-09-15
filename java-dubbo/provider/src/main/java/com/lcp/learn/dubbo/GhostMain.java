package com.lcp.learn.dubbo;

import com.lcp.learn.dubbo.config.DubboConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.locks.LockSupport;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/3/2-18:05
 */
public class GhostMain {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
    // new CountDownLatch(1).await();
    LockSupport.park();

  }

}
