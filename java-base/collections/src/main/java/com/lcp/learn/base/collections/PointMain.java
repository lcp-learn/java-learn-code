package com.lcp.learn.base.collections;

import com.alibaba.fastjson.JSON;
import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/7-15:44
 */
public class PointMain {

  public static void main(String[] args) {

    var user3 = new User("lcp3", 33);
    System.out.println("user3 = " + JSON.toJSONString(user3));

    doSomething(user3);

    System.out.println("user3 = " + JSON.toJSONString(user3));

  }

  private static void doSomething(User user) {
    try {
      var newUser = new User();

      var newOne = user.hashCode();
      newUser.setName("lcp_" + newOne);
      newUser.setAge(newOne);
      newUser.getAddress().setEmail("444@444.com");

      System.out.println("newOne = " + JSON.toJSONString(newUser));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
