package com.lcp.learn.frameworks.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcp.learn.frameworks.mybatisplus.beans.MyOrder;
import org.apache.ibatis.annotations.Param;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:39
 */
public interface OrderDao extends BaseMapper<MyOrder> {

  int queryCount(@Param("id") Long id);
}
