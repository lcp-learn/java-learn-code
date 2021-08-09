package com.lcp.learn.frameworks.mybatis.dao;

import org.apache.ibatis.jdbc.SQL;

public class OrderDaoProvider {

  public String list() {
    return new SQL()
        .SELECT("*")
        .FROM("category_")
        .toString();
  }

  public String get() {
    return new SQL()
        .SELECT("*")
        .FROM("category_")
        .WHERE("id=#{id}")
        .toString();
  }

  public String add() {
    return new SQL()
        .INSERT_INTO("category_")
        .VALUES("name", "#{name}")
        .toString();
  }

  public String update() {
    return new SQL()
        .UPDATE("category_")
        .SET("name=#{name}")
        .WHERE("id=#{id}")
        .toString();
  }

  public String delete() {
    return new SQL()
        .DELETE_FROM("category_")
        .WHERE("id=#{id}")
        .toString();
  }

}
