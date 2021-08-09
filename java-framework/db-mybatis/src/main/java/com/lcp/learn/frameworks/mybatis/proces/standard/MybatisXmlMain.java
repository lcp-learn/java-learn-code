package com.lcp.learn.frameworks.mybatis.proces.standard;

import com.lcp.learn.frameworks.mybatis.dao.OrderDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public class MybatisXmlMain {

  private static final Logger logger = LoggerFactory.getLogger(MybatisXmlMain.class);

  public static void main(String[] args) throws Exception {

    var resource = "mybatis-config.xml";
    var inputStream = Resources.getResourceAsStream(resource);
    var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    var sqlSession = sqlSessionFactory.openSession();

    var orderDao = sqlSession.getMapper(OrderDao.class);
    var count = orderDao.selectCount(11L);
    logger.info("count:{}", count);

  }
}
