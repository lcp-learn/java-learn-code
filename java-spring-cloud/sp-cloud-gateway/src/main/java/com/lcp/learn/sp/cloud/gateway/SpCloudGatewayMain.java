package com.lcp.learn.sp.cloud.gateway;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.REACTIVE;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/16-17:10
 */
@SpringBootApplication
public class SpCloudGatewayMain {

  public static void main(String[] args) {

    ConfigurableApplicationContext springApplicationBuilder = new SpringApplicationBuilder(SpCloudGatewayMain.class)
        .web(REACTIVE)
        .bannerMode(OFF)
        .run(args);
  }

}
