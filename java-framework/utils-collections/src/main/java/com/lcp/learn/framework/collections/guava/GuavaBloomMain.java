package com.lcp.learn.framework.collections.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/5-15:49
 */
public class GuavaBloomMain {

  private static final Logger logger = LoggerFactory.getLogger(GuavaBloomMain.class);

  public static void main(String[] args) {

    // 放入 1000 万条数据
    int total = 10000000;
    var bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
    for (int i = 0; i < total; i++) {
      bloomFilter.put("" + i);
    }

    // 随机查找 10000 个数
    long startTime = System.currentTimeMillis();
    int count1 = 0;
    int count2 = 0;
    for (int i = 0; i < 10000; i++) {
      int num = new Random().nextInt(100000000);
      if (num < total) {
        count1++;
      }
      String numStr = num + "";
      if (bloomFilter.mightContain(numStr)) {
        count2++;
      }
    }
    logger.info("匹配数据:{}条，耗时:{}ms", count2, (System.currentTimeMillis() - startTime));
    if (count1 != count2) {
      logger.info("误判:{}条", Math.abs(count2 - count1));
    }
  }

}
