package com.lcp.learn.spring.base.listener.handler;

import org.springframework.context.ApplicationEvent;

public interface ApplicationListenerHandler<T extends ApplicationEvent> {

  void handle(T applicationEvent);
}
