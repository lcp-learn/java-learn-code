package com.lcp.learn.frameworks.mybatisplus.process.with.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public class MybatisPlusSpringXmlMain extends AbstractMybatisPlusSpringOperation {

  public static void main(String[] args) throws Exception {
    new MybatisPlusSpringXmlMain().doMain();
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
