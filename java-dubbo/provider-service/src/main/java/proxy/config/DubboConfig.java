package proxy.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/9-17:24
 */
// @Configuration
// @EnableDubbo(scanBasePackages = "com.lcp.learn.dubbo.provider.proxy.api.impls")
@PropertySource("classpath:dubbo-provider-config.properties")
public class DubboConfig {

  @Value("${application.name}")
  private String applicationName;

  @Value("${protocol.name}")
  private String protocolName;

  @Value("${protocol.port}")
  private int protocolPort;

  @Value("${qos.enable}")
  private boolean qosStatus;

  @Value("${qos.port}")
  private int qosPort;

  @Value("${zk.address}")
  private String zkAddress;

  @Bean
  public ApplicationConfig applicationConfig() {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(applicationName);
    applicationConfig.setQosAcceptForeignIp(qosStatus);
    applicationConfig.setQosEnable(qosStatus);
    applicationConfig.setQosPort(qosPort);
    return applicationConfig;
  }

  @Bean
  public ProtocolConfig protocolConfig() {
    ProtocolConfig protocolConfig = new ProtocolConfig();
    protocolConfig.setName(protocolName);
    protocolConfig.setPort(protocolPort);

    return protocolConfig;
  }

  @Bean
  public RegistryConfig registryConfigZK() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setProtocol("zookeeper");
    registryConfig.setAddress(zkAddress);
    registryConfig.setGroup("lcp-dubbo");
    return registryConfig;
  }

  // @Bean
  public RegistryConfig registryConfigRedis() throws IOException {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setProtocol("redis");
    registryConfig.setAddress("127.0.0.1:6479");
    registryConfig.setGroup("lcp-dubbo");
    return registryConfig;
  }

  @Bean
  public ProviderConfig providerConfig() {
    ProviderConfig providerConfig = new ProviderConfig();
    providerConfig.setTimeout(1000);
    return providerConfig;
  }

}
