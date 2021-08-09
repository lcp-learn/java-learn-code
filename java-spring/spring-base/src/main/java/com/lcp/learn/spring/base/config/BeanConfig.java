package com.lcp.learn.spring.base.config;

import com.lcp.learn.spring.base.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/8/12-13:55
 */
@Configuration
// @PropertySource("classpath:config.properties")
public class BeanConfig {

  @Bean
  @Profile("haha")
  public User buildUser() {

    User user = new User();
    user.setName("qweqwe");
    user.setAge(110);

    return user;
  }

}
