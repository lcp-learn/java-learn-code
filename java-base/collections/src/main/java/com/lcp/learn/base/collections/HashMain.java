package com.lcp.learn.base.collections;

import org.apache.commons.codec.digest.DigestUtils;
import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/20-13:53
 */
public class HashMain {

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
      var user = new User("lcp" + i, i + 56);
      var firstHash = user.hashCode();
      var secondHash = DigestUtils.md5Hex("" + firstHash);
      var thirdHash = DigestUtils.md5Hex("" + secondHash);
      System.out.println("firstHash = " + firstHash + " ,secondHash=" + secondHash + " ,thirdHash=" + thirdHash);
    }
  }
}
