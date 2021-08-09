package com.lcp.learn.frameworks.mybatisplus.process.with.spring;

import com.lcp.learn.frameworks.mybatisplus.dao.OrderDao;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public abstract class AbstractMybatisPlusSpringOperation {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  protected void action(ApplicationContext applicationContext) throws Exception {

    Arrays.stream(applicationContext.getBeanDefinitionNames())
        .forEach(beanName -> logger.info("name -> {}", beanName));

    var factoryMap = applicationContext.getBeansOfType(SqlSessionFactory.class);
    factoryMap.forEach((key, value) ->
        logger.info("key:{},\tvalue:{}", key, value.getClass().getName()));

    getDao(applicationContext);
    getDao(applicationContext);
  }

  protected void getDao(ApplicationContext applicationContext) throws Exception {

    var sqlSessionFactory1 = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory1");

    var sqlSession = sqlSessionFactory1.openSession();

    OrderDao orderDao = CompletableFuture.supplyAsync(() -> {

      logger.info("config type:{}", getConfigType());

      try {
        return applicationContext.getBeansOfType(OrderDao.class).values().stream().findFirst().get();
      } catch (Exception ex) {
        // ex.printStackTrace();
        return null;
      }

    }).thenApply(orderDao1 -> {

      var dao2 = sqlSession.getMapper(OrderDao.class);

      if (orderDao1 == null) {
        logger.info("applicationContext no dao. get dao from sqlSession");
        return dao2;
      } else {
        logger.info("applicationContext have dao");
        logger.info("applicationContext dao hashcode :{},class:{},sqlsession dao hashcode: {},class:{}",
            orderDao1.hashCode(), orderDao1.getClass().getName(),
            dao2.hashCode(), dao2.getClass().getName());
        return orderDao1;
      }
    }).get();

    var count = orderDao.queryCount(11L);

    sqlSession.close();

    logger.info("count:{}", count);

  }

  protected abstract String getConfigType();
}
