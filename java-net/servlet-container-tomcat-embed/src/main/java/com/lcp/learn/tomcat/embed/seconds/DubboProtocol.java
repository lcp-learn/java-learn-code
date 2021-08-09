package com.lcp.learn.tomcat.embed.seconds;

import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.Processor;
import org.apache.coyote.UpgradeProtocol;
import org.apache.coyote.UpgradeToken;
import org.apache.juli.logging.Log;
import org.apache.tomcat.util.net.AbstractEndpoint;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SocketWrapperBase;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/11-16:17
 */
public class DubboProtocol extends AbstractProtocol {

  public DubboProtocol(AbstractEndpoint endpoint) {
    super(endpoint);
  }

  /**
   * Concrete implementations need to provide access to their logger to be used by the abstract classes.
   *
   * @return the logger
   */
  @Override
  protected Log getLog() {
    return null;
  }

  /**
   * Obtain the prefix to be used when construction a name for this protocol handler. The name will be prefix-address-port.
   *
   * @return the prefix
   */
  @Override
  protected String getNamePrefix() {
    return null;
  }

  /**
   * Obtain the name of the protocol, (Http, Ajp, etc.). Used with JMX.
   *
   * @return the protocol name
   */
  @Override
  protected String getProtocolName() {
    return null;
  }

  /**
   * Find a suitable handler for the protocol negotiated at the network layer.
   *
   * @param name The name of the requested negotiated protocol.
   * @return The instance where {@link UpgradeProtocol#getAlpnName()} matches the requested protocol
   */
  @Override
  protected UpgradeProtocol getNegotiatedProtocol(String name) {
    return null;
  }

  /**
   * Find a suitable handler for the protocol upgraded name specified. This is used for direct connection protocol selection.
   *
   * @param name The name of the requested negotiated protocol.
   * @return The instance where {@link UpgradeProtocol#getAlpnName()} matches the requested protocol
   */
  @Override
  protected UpgradeProtocol getUpgradeProtocol(String name) {
    return null;
  }

  /**
   * Create and configure a new Processor instance for the current protocol implementation.
   *
   * @return A fully configured Processor instance that is ready to use
   */
  @Override
  protected Processor createProcessor() {
    return null;
  }

  @Override
  protected Processor createUpgradeProcessor(SocketWrapperBase socket, UpgradeToken upgradeToken) {
    return null;
  }

  /**
   * Add a new SSL configuration for a virtual host.
   *
   * @param sslHostConfig the configuration
   */
  @Override
  public void addSslHostConfig(SSLHostConfig sslHostConfig) {

  }

  /**
   * Find all configured SSL virtual host configurations which will be used by SNI.
   *
   * @return the configurations
   */
  @Override
  public SSLHostConfig[] findSslHostConfigs() {
    return new SSLHostConfig[0];
  }

  /**
   * Add a new protocol for used by HTTP/1.1 upgrade or ALPN.
   *
   * @param upgradeProtocol the protocol
   */
  @Override
  public void addUpgradeProtocol(UpgradeProtocol upgradeProtocol) {

  }

  /**
   * Return all configured upgrade protocols.
   *
   * @return the protocols
   */
  @Override
  public UpgradeProtocol[] findUpgradeProtocols() {
    return new UpgradeProtocol[0];
  }
}
