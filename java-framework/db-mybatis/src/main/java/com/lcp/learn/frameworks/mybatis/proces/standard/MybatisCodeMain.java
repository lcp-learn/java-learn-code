package com.lcp.learn.frameworks.mybatis.proces.standard;

import com.alibaba.druid.pool.DruidDataSource;
import com.lcp.learn.frameworks.mybatis.dao.OrderDao;
import java.util.Arrays;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-14:12
 */
public class MybatisCodeMain {

  private static final Logger logger = LoggerFactory.getLogger(MybatisCodeMain.class);

  public static void main(String[] args) throws Exception {

    var druidDatasource = new DruidDataSource();
    druidDatasource.setUsername("root");
    druidDatasource.setPassword("aaaaaa");
    druidDatasource.setUrl("jdbc:mysql://127.0.0.1:3316/simple_order");
    druidDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");

    var transactionFactory = new JdbcTransactionFactory();
    var environment = new Environment("development", transactionFactory, druidDatasource);
    var configuration = new Configuration(environment);

    //刷mapp文件!!!
    var mapperLocations =
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");

    Arrays.stream(mapperLocations).forEach(mapperLocation -> {
      try {
        var xmlMapperBuilder = new XMLMapperBuilder(
            mapperLocation.getInputStream(), configuration, mapperLocation.toString(), configuration.getSqlFragments());

        xmlMapperBuilder.parse();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });

    // Resource mapperLocation = null;
    var sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    var orderDao = sqlSessionFactory.openSession().getMapper(OrderDao.class);
    var count = orderDao.selectCount(12L);
    logger.info("count -> {}", count);

  }
}
