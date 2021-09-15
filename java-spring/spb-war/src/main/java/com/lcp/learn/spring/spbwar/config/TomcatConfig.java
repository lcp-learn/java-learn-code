package com.lcp.learn.spring.spbwar.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/8/27-16:03
 */
@Configuration
public class TomcatConfig {

  @Bean
  public TomcatServletWebServerFactory tomcatFactory() {
    return new TomcatServletWebServerFactory() {

      @Override
      protected void postProcessContext(Context context) {
        ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
      }
    };
  }
}
