package com.lcp.learn.spring.spb.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/20-19:44
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1900)
public class SessionConfig {

  @Bean
  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
    // return new GenericFastJsonRedisSerializer();
    return new GenericJackson2JsonRedisSerializer();
    // return new JdkSerializationRedisSerializer();
  }

  @Bean
  public RedisTemplate<Object, Object>
  buildRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

    StringRedisSerializer stringSerializer = new StringRedisSerializer();
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    // objectMapper.activateDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    RedisTemplate redisTemplate = new RedisTemplate<>();

    redisTemplate.setKeySerializer(stringSerializer);
    redisTemplate.setValueSerializer(stringSerializer);
    redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);

    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.afterPropertiesSet();

    return redisTemplate;
  }

}
