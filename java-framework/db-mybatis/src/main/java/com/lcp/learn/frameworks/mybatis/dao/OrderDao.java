package com.lcp.learn.frameworks.mybatis.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * desc:    <br/> r
 *
 * @author lichunpeng
 * @since 2021/2/7-14:39
 */
public interface OrderDao {

  int selectCount(@Param("id") Long id);

  @InsertProvider(type = OrderDaoProvider.class, method = "add")
  public int add(Object category);

}
