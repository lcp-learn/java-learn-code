package com.lcp.learn.dubbo.spb;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.REACTIVE;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-13:00
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.lcp.learn.dubbo.spb.provider")
public class DubboSpbStarter {

  public static void main(String[] args) {

    SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(DubboSpbStarter.class);
    springApplicationBuilder
        .bannerMode(OFF)
        .web(REACTIVE)
        .run(args);

  }
}
