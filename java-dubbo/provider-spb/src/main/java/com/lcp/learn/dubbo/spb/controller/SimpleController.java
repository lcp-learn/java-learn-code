package com.lcp.learn.dubbo.spb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.lcp.learn.dubbo.api.HelloApi;
import com.lcp.learn.dubbo.beans.Sex;
import com.lcp.learn.dubbo.beans.User;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/20-17:02
 */
@RestController
@RequestMapping("/simple")
public class SimpleController {

  private final Logger logger = LoggerFactory.getLogger(SimpleController.class);

  @Resource
  private HelloApi helloApi;

  @RequestMapping(value = "/getUser", method = {POST, GET})
  public User getUser(String name, Sex sex) {
    logger.info("simple getUser name:{},sex:{}", name, sex);
    return helloApi.hello(name, sex);
  }


}
