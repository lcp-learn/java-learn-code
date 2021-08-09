package com.lcp.learn.base.collections.stream;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.lcp.learn.java.beans.User;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-27-11:01
 */
public class LambdaMain {

  public static void main(String[] args) {

    var list = Arrays.asList(1, 2, 3, 4, 5, 5, 5, 5);
    System.out.println("list = " + list);

    var list2 = list.stream().filter(item -> item > 3).collect(Collectors.toList());
    System.out.println("list = " + list2);

    list.stream().map(item -> item + 9).forEach(item -> System.out.println("item = " + item));

    var total = list.stream().reduce((sum, x) -> sum * x).get();
    System.out.println("total = " + total);

    var list3 = list.stream().distinct().collect(Collectors.toList());
    System.out.println("list3 = " + list3);

    var a1 = Optional.of(new User("haha", 88));
    System.out.println("a1 = " + a1);
    a1.ifPresent(item -> System.out.println("item = " + JSON.toJSONString(item)));

    var count = list.stream().distinct().count();
    System.out.println("count = " + count);

    var stream1 = Arrays.stream(new String[]{"qqq", "www", "eee"});
    var stream2 = Stream.of("aaa", "sss", "ddd");

    var userList = new LinkedList<User>();
    // for (int i = 0; i < 1000; i++) {
    //     userList.add(new User("lcp" + i, 10 + i));
    // }

    userList.add(new User("lcp10", 10));
    userList.add(new User("lcp11", 10));
    userList.add(new User("lcp12", 11));
    userList.add(new User("lcp13", 11));

    long start = System.currentTimeMillis();

    var newList =
        userList.stream().sorted(Comparator.comparing(User::getAge).reversed())
            .sorted(Comparator.comparing(User::getName).reversed()).collect(Collectors.toList());
    System.out.println("newList = " + JSON.toJSONString(newList));
    System.out.println("newList.0 = " + newList.get(0));
    System.out.println("newList.size = " + newList.size());

    long end = System.currentTimeMillis();

    System.out.println("sorted cost = " + (end - start));

    var a = Stream.of("a", "b", "c", "b");
    a.distinct().peek(item -> System.out.println("item = " + item)).collect(Collectors.toList());

    var integerStream = Stream.of(1, 2, 5, 7, 8, 12, 33);
    Integer sum = integerStream.reduce(0, (x, y) -> x + y);
    System.out.println(sum);

    IntStream.range(1, 10).peek(item -> System.out.println("itemPeek = " + item))
        .forEach(item -> System.out.println("item = " + item));

  }

}
