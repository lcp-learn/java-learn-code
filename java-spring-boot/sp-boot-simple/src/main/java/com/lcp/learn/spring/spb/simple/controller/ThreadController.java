package com.lcp.learn.spring.spb.simple.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/thread")
public class ThreadController {

  private final Logger logger = LoggerFactory.getLogger(ThreadController.class);

  @RequestMapping(value = "/park", method = {POST, GET})
  public String park() {

    // LockSupport.park();

    return "park";
  }

  @RequestMapping(value = "/sleep", method = {POST, GET})
  public String sleep() {

    // try {
    //   Thread.sleep(Integer.MAX_VALUE);
    // } catch (Exception ex) {
    //   ex.printStackTrace();
    // }

    return "sleep";
  }

}
