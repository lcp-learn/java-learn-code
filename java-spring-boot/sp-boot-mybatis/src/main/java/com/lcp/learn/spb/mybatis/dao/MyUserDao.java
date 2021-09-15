package com.lcp.learn.spb.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcp.learn.spb.mybatis.beans.MyUser;
import com.lcp.learn.spb.mybatis.cache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

@CacheNamespace(implementation = RedisCache.class)
public interface MyUserDao extends BaseMapper<MyUser> {

    @Options
    String getNameById(long id);

    int getAgeById(long id);
}
