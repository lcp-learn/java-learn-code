package com.lcp.learn.spring.base.service.impl;

import com.lcp.learn.spring.base.beans.animal.Animal;
import com.lcp.learn.spring.base.service.AbstractService;
import com.lcp.learn.spring.base.service.HelloService;

public class HelloServiceImpl extends AbstractService implements HelloService {

  @Override
  public Animal getAnimal() {
    return animal;
  }
}
