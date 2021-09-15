package com.lcp.learn.dubbo.spb.mini.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/13-18:58
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = "com.lcp.learn.dubbo.spb.mini.provider")
public class CacheConfig {

  public static final int DEFAULT_MAXSIZE = 10000;
  public static final int DEFAULT_TTL = 600;

  /**
   * 创建基于Caffeine的Cache Manager
   *
   * @return
   */
  @Bean
  @Primary
  public CacheManager caffeineCacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    List<CaffeineCache> cachesList = new ArrayList<CaffeineCache>();
    for (Caches caches : Caches.values()) {
      cachesList.add(new CaffeineCache(caches.name(),
          Caffeine.newBuilder().recordStats().expireAfterWrite(caches.getTtl(), TimeUnit.SECONDS)
              .maximumSize(caches.getMaxSize()).build()));
    }
    cacheManager.setCaches(cachesList);
    return cacheManager;
  }

  /**
   * 定义cache名称、超时时长（秒）、最大容量 每个cache缺省：10秒超时、最多缓存50000条数据，需要修改可以在构造方法的参数中指定。
   */
  public enum Caches {
    getUserById(30),          //有效期600秒
    listCustomers(7200, 1000),  //有效期2个小时 , 最大容量1000
    ;

    private int maxSize = DEFAULT_MAXSIZE;    //最大數量
    private int ttl = DEFAULT_TTL;        //过期时间（秒）

    Caches() {
    }

    Caches(int ttl) {
      this.ttl = ttl;
    }

    Caches(int ttl, int maxSize) {
      this.ttl = ttl;
      this.maxSize = maxSize;
    }

    public int getMaxSize() {
      return maxSize;
    }

    public int getTtl() {
      return ttl;
    }
  }

}
