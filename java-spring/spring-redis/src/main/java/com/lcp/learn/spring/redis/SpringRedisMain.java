package com.lcp.learn.spring.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/24-18:00
 */
public class SpringRedisMain {

  private static final Logger logger = LoggerFactory.getLogger(SpringRedisMain.class);

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationcontext =
        new AnnotationConfigApplicationContext("com.lcp.learn.spring.redis.config");

    RedisTemplate<String, String> redisTemplate = applicationcontext.getBean(RedisTemplate.class);
    String key = "dname";
    redisTemplate.opsForValue().set(key, "中文333");
    String result = redisTemplate.opsForValue().get(key);
    logger.info("result:{}", result);
    System.out.println("result = " + result);

  }

}
