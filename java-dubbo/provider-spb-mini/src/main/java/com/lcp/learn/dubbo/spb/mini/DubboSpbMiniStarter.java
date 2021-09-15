package com.lcp.learn.dubbo.spb.mini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import static org.springframework.boot.Banner.Mode.OFF;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-13:00
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo-provider-beans.xml"})
public class DubboSpbMiniStarter {

  private static final Logger logger = LoggerFactory.getLogger(DubboSpbMiniStarter.class);

  public static void main(String[] args) {

    SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(DubboSpbMiniStarter.class);

    springApplicationBuilder
        .bannerMode(OFF)
        .web(WebApplicationType.NONE)
        .run(args);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> logger.info("server close ...")));

  }
}
