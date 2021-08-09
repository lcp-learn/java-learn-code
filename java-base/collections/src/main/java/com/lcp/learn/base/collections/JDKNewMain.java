package com.lcp.learn.base.collections;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/4/10-10:29
 */
public class JDKNewMain {

  public static void main(String[] args) {

    RandomStringUtils.random(10, true, true);

    var user1 = getUser("lcp", INTEGER_ZERO, null);
    var user2 = getUser(null, INTEGER_ZERO, null);

  }

  /**
   * 得到用户
   *
   * @param name    名称
   * @param age     年龄
   * @param address 地址
   * @return
   */
  private static Optional<User> getUser(String name, int age, String address) {

    return StringUtils.isEmpty(name) ? Optional.empty() : Optional.of(new User());
  }

}
