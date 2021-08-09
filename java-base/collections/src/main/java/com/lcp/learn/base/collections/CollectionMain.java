package com.lcp.learn.base.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.lcp.learn.java.beans.Order;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/2/26-11:17
 */
public class CollectionMain {

  public static void main(String[] args) {

    //        var sortUsers = new LinkedList<SortUser>();
    //        sortUsers.add(new SortUser("lcp2", 223));
    //        sortUsers.add(new SortUser("lcp1", 123));
    //        sortUsers.add(new SortUser("lcp4", 423));
    //        sortUsers.add(new SortUser("lcp3", 323));
    //
    //        Collections.sort(sortUsers);
    //        System.out.println("sortUsers = " + JSON.toJSONString(sortUsers));
    //
    //        var newList1 = sortUsers.parallelStream()
    //                .filter(sortUser -> sortUser.getAge() > 250)
    //                .collect(Collectors.toList());
    //
    //        System.out.println("newList1 = " + JSON.toJSONString(newList1));
    //
    //        var numbers = Arrays.asList(1, 2, 3, 4, 6, 11, 22, 33, 44, 55, 66);
    //        numbers.parallelStream()
    //                .forEach(System.out::println);

    //      var spiterator=  numbers.spliterator();
    //        numbers.spliterator().trySplit().forEachRemaining(integer ->
    //                System.out.println("integer = " + integer));

    //        var arrayList = new ArrayList<Integer>(3);
    //        for (int i = 0; i < 10; i++) {
    //            arrayList.add(i);
    //        }
    //
    //        arrayList.remove(2);
    //        arrayList.addAll(Lists.newArrayList(111));
    //        arrayList.contains(33);
    //
    //        var linkedList = new LinkedList<Integer>();
    //        linkedList.add(11);
    //        linkedList.remove(Integer.valueOf(22));

    //        var queue = new PriorityQueue<Integer>();
    //        for (int i = 0; i < 5; i++) {
    //            queue.offer(i);
    //        }
    //        System.out.println("queue = " + queue);
    //        queue.poll();
    //        System.out.println("queue = " + queue);
    //        queue.peek();
    //        System.out.println("queue = " + queue);

    //        var treeSet = new TreeSet<Integer>();
    //
    //        SortedSet<Integer> sortedSet = treeSet;
    //        treeSet.add(22);
    //        treeSet.add(11);
    //        treeSet.add(44);
    //        treeSet.add(33);
    //        System.out.println("sortedSet = " + treeSet);
    //        System.out.println("sortedSet.first = " + treeSet.first());

    var map = new HashMap<String, String>();
    for (int i = 0; i < 5; i++) {
      map.put(RandomStringUtils.random(10, true, false), i + "_" + RandomStringUtils.random(10, true, false));

    }
    //        System.out.println("map = " + map);
    map.forEach((key, value) -> {
      System.out.println("item.key = " + key);
      System.out.println("item.value = " + value);
    });

    var map1 = Collections.<String, String>emptyMap();

    List<Order> orderList = new LinkedList<>();
    orderList.add(new Order(1001L, 201L, 19));
    orderList.add(new Order(1001L, 202L, 198));
    orderList.add(new Order(1002L, 201L, 398));

    Map<Long, List<Order>> orderMap = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));

    System.out.println("orderMap = " + orderMap);

    orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));

    orderMap.keySet().forEach(orderId -> {
      Order tmp = orderMap.get(orderId).stream().reduce((order, order2) -> {
        order.setMoney(order.getMoney() + order2.getMoney());
        return order;
      }).get();

      System.out.println("orderId = " + orderId + ",money=" + tmp.getMoney());
    });

    Map<String, List<String>> map3 = new HashMap<>();

    // 如果key不存在，则创建新list并放入数据；key存在，则直接往list放入数据
    map3.computeIfAbsent("ProgrammingLanguage", k -> new ArrayList<>()).add("Java");
    map3.computeIfAbsent("ProgrammingLanguage", k -> new ArrayList<>()).add("Python");
    map3.computeIfAbsent("ProgrammingLanguage", k -> new ArrayList<>()).add("C#");
    map3.computeIfAbsent("Database", k -> new ArrayList<>()).add("Mysql");
    map3.computeIfAbsent("Database", k -> new ArrayList<>()).add("Oracle");

    // 遍历
    map3.forEach((k, v) -> System.out.println(k + " " + v));

  }
}
