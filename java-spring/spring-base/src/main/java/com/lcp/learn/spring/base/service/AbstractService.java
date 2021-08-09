package com.lcp.learn.spring.base.service;

import com.lcp.learn.spring.base.beans.animal.Animal;
import com.lcp.learn.spring.base.event.MyApplicationEvent;
import com.lcp.learn.spring.base.event.MyEvents;
import com.lcp.learn.spring.base.listener.handler.ApplicationListenerHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//public abstract class AbstractService implements ApplicationListenerHandler<ChangeBeanApplicationEvent>,
// ApplicationContextAware {
public abstract class AbstractService implements ApplicationListenerHandler<MyApplicationEvent>,
    ApplicationContextAware {

  protected Animal animal;
  private ApplicationContext applicationContext;

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(MyApplicationEvent applicationEvent) {
    if (applicationEvent.getSource() == MyEvents.CHANGE_BEAN) {
      animal = applicationContext.getBean("animal", Animal.class);
    }

  }
}
