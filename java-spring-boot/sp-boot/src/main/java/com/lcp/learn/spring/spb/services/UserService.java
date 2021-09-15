package com.lcp.learn.spring.spb.services;

import com.lcp.learn.spring.spb.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/10/24-10:15
 */
public interface UserService {

  /**
   * 得到用户信息
   *
   * @param name
   * @return
   */
  User getUser(String name);
}
