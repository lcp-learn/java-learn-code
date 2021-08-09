package com.lcp.learn.spring.base.event;

import org.springframework.context.ApplicationEvent;

public class ServiceEvent extends ApplicationEvent {

  private static final long serialVersionUID = -4882240019176803154L;

  public ServiceEvent(Object source) {
    super(source);
    System.out.println("source = " + source.getClass().getName());
  }
}
