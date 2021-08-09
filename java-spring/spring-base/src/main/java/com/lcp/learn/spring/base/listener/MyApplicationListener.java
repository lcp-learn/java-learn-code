package com.lcp.learn.spring.base.listener;

import com.google.common.collect.Lists;
import com.lcp.learn.spring.base.listener.handler.ApplicationListenerHandler;
import java.util.List;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

  private List<ApplicationListenerHandler> applicationListenerHandlerList = Lists.newLinkedList();

  /**
   * 注册
   *
   * @param applicationListenerHandler
   */
  public void regist(ApplicationListenerHandler applicationListenerHandler) {
    applicationListenerHandlerList.add(applicationListenerHandler);
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    applicationListenerHandlerList.forEach(applicationListenerHandler ->
        applicationListenerHandler.handle(event));
  }
}
