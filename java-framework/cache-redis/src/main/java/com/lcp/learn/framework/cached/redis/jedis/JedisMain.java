package com.lcp.learn.framework.cached.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/19-10:31
 */
public class JedisMain {

  private static final Logger logger = LoggerFactory.getLogger(JedisMain.class);

  public static void main(String[] args) {

    var jedis = new Jedis("127.0.0.1", 6479);
    // jedis.keys("*").forEach(key -> logger.info("key:{}", key));

    // var name = "k1_name";

    // jedis.setbit("bname1", 9, "1");
    // var b3 = jedis.getbit("bname1", 9);
    // var b9 = jedis.getbit("bname1", 5);
    // logger.info("b3:{}", b3);
    // logger.info("b9:{}", b9);
    //
    // var list1 = jedis.mget("k1_name", "asd", "dfgdfg");
    // list1.forEach(item -> logger.info("item:{}", item));

    jedis.select(4);
    var result = jedis.get("aname");
    logger.info("result:{}", result);

  }

}
