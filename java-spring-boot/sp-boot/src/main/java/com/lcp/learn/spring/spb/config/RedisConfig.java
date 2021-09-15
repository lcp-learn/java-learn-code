package com.lcp.learn.spring.spb.config;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/20-20:12
 */
@Configuration
public class RedisConfig {

  @Autowired
  private Environment environment;

  @Bean
  public RedisConnectionFactory buildRedisConnectionFactory() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(Objects.requireNonNull(environment.getProperty("spring.redis.host")));
    redisStandaloneConfiguration
        .setPort(Objects.requireNonNull(environment.getProperty("spring.redis.port", Integer.class)));
    redisStandaloneConfiguration
        .setDatabase(Objects.requireNonNull(environment.getProperty("spring.redis.database", Integer.class)));
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

}
