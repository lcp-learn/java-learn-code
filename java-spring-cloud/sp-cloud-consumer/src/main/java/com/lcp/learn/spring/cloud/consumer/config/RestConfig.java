package com.lcp.learn.spring.cloud.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-16:16
 */
@Configuration
public class RestConfig {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate;
  }

}
