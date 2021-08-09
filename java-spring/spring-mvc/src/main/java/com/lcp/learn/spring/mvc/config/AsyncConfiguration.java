package com.lcp.learn.spring.mvc.config;

import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/6-14:16
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {

  @Bean
  protected WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(getTaskExecutor());
      }
    };
  }

  @Bean("taskExecutor")
  protected ConcurrentTaskExecutor getTaskExecutor() {
    return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5));
  }

}
