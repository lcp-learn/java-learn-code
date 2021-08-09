package com.lcp.learn.base.collections;

import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/21-13:35
 */
public class ClassMain {

  public static void main(String[] args) {

    var user = new User();

    System.out.println("loader name = " + User.class.getClassLoader().getClass().getName());
    System.out.println("loader name = " + String.class.getClassLoader().getName());
  }
}
