package com.lcp.learn.base.collections.func;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/2-17:42
 */
public class FunctionMain {

  private static final Logger logger = LoggerFactory.getLogger(FunctionMain.class);

  public static void main(String[] args) {

    var function = new MyFunc();

    var result = function.apply(45);
    logger.info("result = {}", result);

    Function<String, Integer> function2 = Integer::parseInt;

    Integer i = function2.apply("10");
    logger.info("i = {}", i);

    var function3 = new Function<Integer, String>() {
      @Override
      public String apply(Integer source) {
        int a = 99;
        int b = a + source;
        return "hello:" + b;
      }
    };

    String value1 = function3.apply(56);
    logger.info("value1 = {}", value1);

    Comparator<Integer> comparator = Integer::compare;
    int result2 = comparator.compare(100, 10);
    logger.info("result2 = {}", result2);

    IntBinaryOperator intBinaryOperator = Integer::compare;
    int result3 = intBinaryOperator.applyAsInt(100, 10);
    logger.info("result3 = {}", result3);

    var kite1 = new KiteFunction<LocalDateTime, String, String>() {
      @Override
      public String kiteRun(LocalDateTime localDateTime, String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
        return localDateTime.format(dateTimeFormatter);
      }
    };

    var result4 = kite1.kiteRun(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
    logger.info(result4);

    Consumer<String> func5 = s -> logger.info("s = {}", s);

    func5.accept("zxczxc");

  }
}
