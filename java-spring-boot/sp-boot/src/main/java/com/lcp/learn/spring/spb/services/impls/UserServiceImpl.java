package com.lcp.learn.spring.spb.services.impls;

import com.lcp.learn.spring.spb.beans.User;
import com.lcp.learn.spring.spb.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/10/24-10:16
 */
@Service
public class UserServiceImpl implements UserService {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private Map<String, User> userMap = null;

  @PostConstruct
  public void init() {

    userMap = new HashMap<>();
    userMap.put("zhangsan", new User("zhangsan", 123));
    userMap.put("lisi", new User("lisi", 567));
  }

  /**
   * 得到用户信息
   *
   * @param name
   * @return
   */
  @Override
  // @Cacheable(value = "userInfo", key = "targetClass + methodName +#p0")
  public User getUser(String name) {
    logger.info("name:{}", name);
    return userMap.get(name);
  }
}
