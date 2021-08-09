package com.lcp.learn.base.collections;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.lcp.learn.java.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilsMain {

  private static final Logger logger = LoggerFactory.getLogger(UtilsMain.class);

  public static void main(String[] args) {

    var list = new ArrayList<User>();
    for (int i = 0; i < 5; i++) {
      var user = new User();
      user.setAge(i);
      user.setName("uu_" + i);
      list.add(user);
    }

    var target = list.stream().max(Comparable::compareTo).orElse(new User());

    logger.info("json = {}", JSON.toJSONString(target));

    var value2 = list.stream().collect(Collectors.summarizingInt(User::getAge));

    logger.info("value2:{}", value2);

    var hashmap = new HashMap<String, String>();
    hashmap.put("asd", "Asd");

    var aa = tableSizeFor(15);
    logger.info("aa = {}", aa);

  }

  private static int tableSizeFor(int cap) {
    int MAXIMUM_CAPACITY = 1 << 30;
    int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

}
