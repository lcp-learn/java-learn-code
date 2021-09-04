package com.lcp.learn.spb.mybatis.config;

import javax.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * desc:    <br/>
 * @since 2020/10/14-15:15
 * @author lichunpeng
 */
@Configuration
@MapperScan({"com.lcp.learn.spb.mybatis.dao"})
public class MybatisConfig {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public HashOperations<String, String, byte[]> buildHashOperations() {
        return redisTemplate.opsForHash();
    }

    @Bean
    public RedisConnectionFactory buildRedisConnectionFactory() {
        // return new JedisConnectionFactory();
      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPort(6479);
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setDatabase(0);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
