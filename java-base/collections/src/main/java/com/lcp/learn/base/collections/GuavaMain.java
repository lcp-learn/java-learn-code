package com.lcp.learn.base.collections;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/25-09:48
 */
public class GuavaMain {

  private static final Logger logger = LoggerFactory.getLogger(GuavaMain.class);

  public static void main(String[] args) {
    Integer nubmerone1 = null;
    //one
    var possible = Optional.fromNullable(nubmerone1).or(567);  //创建允许null值的Optional
    logger.info("possible:{}", possible);
    //two
    Integer nubmerone = 444;
    var integerOptional = Optional.of(nubmerone);//若引用为null则快速失败触发java.lang.NullPointerException
    logger.info("integerOptional:{}", integerOptional);
    //three
    var nullOptional = Optional.absent();//创建引用缺失的Optional实例,就是为NULL的
    logger.info("nullOptional:{}", nullOptional);

  }

}
