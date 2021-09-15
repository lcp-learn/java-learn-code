package com.lcp.learn.spring.spb.webflux;

import com.lcp.learn.spring.spb.webflux.config.WebFluxConfig;
import java.util.concurrent.locks.LockSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/7-13:38
 */
public class SpringWebfluxApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringWebfluxApplication.class);

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebFluxConfig.class);
    //通过ApplicationContext创建HttpHandler
    HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
    ReactorHttpHandlerAdapter httpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
    HttpServer httpServer = HttpServer.create();
    httpServer.host("127.0.0.1");
    httpServer.port(8097);
    httpServer.handle(httpHandlerAdapter).bind().block();

    logger.info("start....");
    LockSupport.park();
  }
}
