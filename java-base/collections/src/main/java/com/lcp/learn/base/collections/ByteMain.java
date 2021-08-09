package com.lcp.learn.base.collections;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/3-15:41
 */
public class ByteMain {

  private static final Logger logger = LoggerFactory.getLogger(ByteMain.class);

  public static void main(String[] args) throws Exception {

    logger.info("{}", ByteOrder.nativeOrder());
    //        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
    //            System.out.println("BIG_ENDIAN");
    //        } else {
    //            System.out.println("LITTLE_ENDIAN");
    //        }

    char[] chars = new char[]{'\u0097'};
    String str = new String(chars);
    byte[] bytes = str.getBytes();
    logger.info("{}", Arrays.toString(bytes));

  }

}
