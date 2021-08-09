package com.lcp.learn.spring.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/24-18:00
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisConnectionFactory buildRedisConnectionFactory() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName("127.0.0.1");
    redisStandaloneConfiguration.setPort(6479);
    redisStandaloneConfiguration.setDatabase(4);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  public RedisTemplate<String, String> buildRedisTemplate() {

    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(buildRedisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    //
    return redisTemplate;
  }
}
