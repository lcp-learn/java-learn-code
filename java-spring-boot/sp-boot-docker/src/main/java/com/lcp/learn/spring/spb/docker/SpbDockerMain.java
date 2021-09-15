package com.lcp.learn.spring.spb.docker;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lcp
 * @since 2019/9/16-15:46
 */
@SpringBootApplication
public class SpbDockerMain {

  private static final Logger logger = LoggerFactory.getLogger(SpbDockerMain.class);

  public static void main(String[] args) {

    disableAccessWarnings();
    ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpbDockerMain.class)
        .bannerMode(OFF)
        .web(SERVLET)
        // .web(NONE)
        .run(args);
  }

  /**
   * 忽略非法反射警告  适用于jdk11
   */
  @SuppressWarnings("unchecked")
  public static void disableAccessWarnings() {
    try {
      Class unsafeClass = Class.forName("sun.misc.Unsafe");
      Field field = unsafeClass.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      Object unsafe = field.get(null);

      Method putObjectVolatile = unsafeClass
          .getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
      Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

      Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
      Field loggerField = loggerClass.getDeclaredField("logger");
      Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
      putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
    } catch (Exception ignored) {
    }
  }
}
