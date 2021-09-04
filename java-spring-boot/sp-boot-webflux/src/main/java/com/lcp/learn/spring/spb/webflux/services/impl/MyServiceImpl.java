package com.lcp.learn.spring.spb.webflux.services.impl;

import com.lcp.learn.spring.spb.webflux.beans.User;
import com.lcp.learn.spring.spb.webflux.beans.UserBuilder;
import com.lcp.learn.spring.spb.webflux.services.MyService;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/10-16:31
 */
@Service
public class MyServiceImpl implements MyService {

  private final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

  @Override
  public List<User> getAllUser(int count) {

    List<User> userList = new LinkedList<>();

    int start = RandomUtils.nextInt(0, 999);
    for (int i = start; i < start + count; i++) {
      userList.add(UserBuilder.anUser().name("name" + i).age(i).build());
    }

    return userList;
  }
}
