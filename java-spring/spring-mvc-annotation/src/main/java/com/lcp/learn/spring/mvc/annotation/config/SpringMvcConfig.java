package com.lcp.learn.spring.mvc.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/9-17:19
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.lcp.learn.spring.mvc.annotation.controller")
public class SpringMvcConfig {

}
