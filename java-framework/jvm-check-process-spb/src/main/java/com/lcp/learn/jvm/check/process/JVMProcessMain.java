package com.lcp.learn.jvm.check.process;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.SERVLET;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/8-15:26
 */
@SpringBootApplication
public class JVMProcessMain {

  public static void main(String[] args) {

    new SpringApplicationBuilder(JVMProcessMain.class)
        .web(SERVLET)
        .bannerMode(OFF)
        .run(args);
  }

}
