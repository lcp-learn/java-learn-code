package com.lcp.learn.framework.logger.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class MyLoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

  private boolean started = false;

  @Override
  public boolean isResetResistant() {
    return false;
  }

  @Override
  public void onStart(LoggerContext context) {

  }

  @Override
  public void onReset(LoggerContext context) {

  }

  @Override
  public void onStop(LoggerContext context) {

  }

  @Override
  public void onLevelChange(Logger logger, Level level) {

  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isStarted() {
    return false;
  }

  @Override
  public void start() {
    if (started) {
      return;
    }
    Context context = getContext();
    context.putProperty("localIP", getUniqName());
    started = true;
  }

  private String getUniqName() {
    String localIp = null;
    try {
      localIp = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      //LOG.error("fail to get ip...", e);
    }
    String uniqName = UUID.randomUUID().toString().replace("-", "");
    if (localIp != null) {
      uniqName = localIp + "-" + uniqName;
    }
    return uniqName;
  }
}
