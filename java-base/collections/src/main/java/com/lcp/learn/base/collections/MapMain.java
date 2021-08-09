package com.lcp.learn.base.collections;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.lcp.learn.java.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/8-13:47
 */
public class MapMain {

  private static final Logger logger = LoggerFactory.getLogger(MapMain.class);

  public static void main(String[] args) {

    var map = new HashMap<String, User>(32);

    IntStream.range(1001, 1005).boxed()
        .forEach(id -> map.putIfAbsent(id.toString(), new User()));

    List<String> list2 = map.entrySet().stream()
        .map(entry -> entry.getKey() + "->" + entry.getValue().hashCode())
        .collect(Collectors.toList());

    logger.info("{}", JSON.toJSONString(list2));

    List<Integer> codeList = IntStream.range(0, 20).boxed()
        .map(item -> RandomUtils.nextInt(1111, 9999))
        .collect(Collectors.toList());
    logger.info("{}", JSON.toJSONString(codeList));

    List<String> codeStringList = IntStream.range(0, 10).boxed()
        .map(item -> RandomStringUtils.random(2 << 4, true, true))
        .collect(Collectors.toList());
    logger.info("{}", JSON.toJSONString(codeStringList));

  }
}
