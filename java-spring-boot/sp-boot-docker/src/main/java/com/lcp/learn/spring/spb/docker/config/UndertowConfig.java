package com.lcp.learn.spring.spb.docker.config;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/10-14:52
 */
@Configuration
public class UndertowConfig implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

  @Override
  public void customize(UndertowServletWebServerFactory undertowServletWebServerFactory) {
    undertowServletWebServerFactory.addDeploymentInfoCustomizers(deploymentInfo -> {
      WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
      webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
      deploymentInfo
          .addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
    });
  }

}
