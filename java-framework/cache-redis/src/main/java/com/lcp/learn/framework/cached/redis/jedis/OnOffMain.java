package com.lcp.learn.framework.cached.redis.jedis;

import static com.lcp.learn.framework.cached.redis.jedis.Switcher.USE_CHECK_F;
import static com.lcp.learn.framework.cached.redis.jedis.Switcher.USE_CHECK_S;
import static com.lcp.learn.framework.cached.redis.jedis.Switcher.USE_GATEWAY;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/19-10:31
 */
public class OnOffMain {

  private static final Logger logger = LoggerFactory.getLogger(OnOffMain.class);

  public static void main(String[] args) {

    var jedis = new Jedis("127.0.0.1", 6479);

    var switcherName = "switcher_name";
    jedis.setbit(switcherName, USE_GATEWAY.getCode(), INTEGER_ONE.toString());
    jedis.setbit(switcherName, USE_CHECK_F.getCode(), INTEGER_ZERO.toString());
    jedis.setbit(switcherName, USE_CHECK_S.getCode(), INTEGER_ONE.toString());

    var b3 = jedis.getbit(switcherName, USE_GATEWAY.getCode());
    var b9 = jedis.getbit(switcherName, USE_CHECK_F.getCode());
    var b11 = jedis.getbit(switcherName, USE_CHECK_S.getCode());
    logger.info("USE_GATEWAY:{}", b3);
    logger.info("USE_CHECK_F:{}", b9);
    logger.info("USE_CHECK_S:{}", b11);

    var count = jedis.bitcount(switcherName);
    logger.info("count:{}", count);

  }

}
