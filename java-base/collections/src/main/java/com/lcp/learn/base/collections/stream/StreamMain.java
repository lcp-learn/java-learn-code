package com.lcp.learn.base.collections.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/23-19:05
 */
public class StreamMain {

  private static final Logger logger = LoggerFactory.getLogger(StreamMain.class);

  public static void main(String[] args) {

    var findStr = "bd";
    var list = Lists.newArrayList("a", "b", "ab", "abc", "a", "ab", "a", "abcd", "bd", "abc");

    var countMap = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    countMap.forEach((key, value) -> logger.info("key:{},value:{}", key, value));

    boolean match = list.stream().anyMatch(x -> x.equals(findStr));
    //结果：match:true
    logger.info("match:{}", match);

    var list2 = Lists.newArrayList(5, 3, 7, 1, 4, 6);
    var collect = list2.stream()
        .sorted(Comparator.comparingInt((ToIntFunction<Integer>) value -> value).reversed())
        .collect(Collectors.toList());
    //结果：[1, 3, 4, 5, 6, 7]
    logger.info("collect:{}", JSON.toJSONString(collect));

    var list3 = Lists.newArrayList(11, 44, 55, 22, 66, 99, 77, 88, 33);

    var pageNumber = 2;
    var pageSize = 3;

    var list4 = list3.stream().sorted()
        .skip((pageNumber - 1) * pageSize).limit(pageSize)
        .collect(Collectors.toList());
    logger.info("list4:{}", JSON.toJSONString(list4));

  }
}
