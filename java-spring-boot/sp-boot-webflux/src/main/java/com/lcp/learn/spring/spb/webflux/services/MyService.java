package com.lcp.learn.spring.spb.webflux.services;

import com.lcp.learn.spring.spb.webflux.beans.User;
import java.util.List;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/10-16:30
 */
public interface MyService {

  List<User> getAllUser(int count);
}
