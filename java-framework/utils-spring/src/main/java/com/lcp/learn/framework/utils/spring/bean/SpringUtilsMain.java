package com.lcp.learn.framework.utils.spring.bean;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/3/31-11:55
 */
public class SpringUtilsMain {

  public static void main(String[] args) {

    List<A> aList = new ArrayList<>();
    aList.add(new A("aaa1"));

    List<B> bList = new ArrayList<>();

    BeanUtils.copyProperties(aList, bList);
    bList.forEach(b -> System.out.println("b = " + b.getName()));
    aList.forEach(a -> System.out.println("a = " + a.getName()));

  }
}
