package com.lcp.learn.spb.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spb.mybatis.beans.MyUser;
import com.lcp.learn.spb.mybatis.dao.MyUserDao;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/14-14:37
 */
@RestController
@RequestMapping("/data")
public class DataController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Resource
  private MyUserDao myUserDao;

  @RequestMapping("/getUser")
  public MyUser getUserInfi(@RequestParam(value = "id") Long id) {

    MyUser user = myUserDao.selectById(id);
    logger.info("user:{}", JSON.toJSONString(user));

    return user;
  }

}
