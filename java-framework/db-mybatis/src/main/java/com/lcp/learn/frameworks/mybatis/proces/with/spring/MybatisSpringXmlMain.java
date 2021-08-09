package com.lcp.learn.frameworks.mybatis.proces.with.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public class MybatisSpringXmlMain extends AbstractMybatisSpringOperation {

  public static void main(String[] args) throws Exception {
    new MybatisSpringXmlMain().doMain();
  }

  private void doMain() throws Exception {
    var applicationContext = new ClassPathXmlApplicationContext("classpath:mybatis-config-spring.xml");
    action(applicationContext);
  }

  @Override
  protected String getConfigType() {
    return "xml";
  }
}
