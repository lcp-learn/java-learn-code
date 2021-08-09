package com.lcp.learn.spring.base.event;

import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = 5297401412442473139L;
  private MyEvents source;

  public MyApplicationEvent(MyEvents source) {
    super(source);
    this.source = source;
  }

  @Override
  public MyEvents getSource() {
    return source;
  }
}
