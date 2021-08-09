package com.lcp.learn.frameworks.mybatis.proces.with.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public class MybatisSpringAnnotationMain extends AbstractMybatisSpringOperation {

  private static final Logger logger = LoggerFactory.getLogger(MybatisSpringAnnotationMain.class);

  public static void main(String[] args) throws Exception {
    new MybatisSpringAnnotationMain().doMain();
  }

  private void doMain() throws Exception {
    var applicationContext =
        new AnnotationConfigApplicationContext("com.lcp.learn.frameworks.mybatis.config");
    action(applicationContext);

  }

  @Override
  protected String getConfigType() {
    return "annotation";
  }
}
