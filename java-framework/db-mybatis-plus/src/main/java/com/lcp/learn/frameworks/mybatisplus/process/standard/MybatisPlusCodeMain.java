package com.lcp.learn.frameworks.mybatisplus.process.standard;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.lcp.learn.frameworks.mybatisplus.beans.MyOrder;
import com.lcp.learn.frameworks.mybatisplus.dao.OrderDao;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-17:32
 */
public class MybatisPlusCodeMain {

  private static final Logger logger = LoggerFactory.getLogger(MybatisPlusCodeMain.class);

  public static void main(String[] args) throws Exception {

    var druidDatasource = new DruidDataSource();
    druidDatasource.setUsername("root");
    druidDatasource.setPassword("aaaaaa");
    druidDatasource.setUrl("jdbc:mysql://127.0.0.1:3316/simple_order");
    druidDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");

    var mybatisConfiguration = new MybatisConfiguration();
    GlobalConfigUtils.getGlobalConfig(mybatisConfiguration).setBanner(false);

    //刷mapp文件!!!
    var mapperLocations =
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");

    Arrays.stream(mapperLocations).forEach(mapperLocation -> {
      try {
        var xmlMapperBuilder = new XMLMapperBuilder(
            mapperLocation.getInputStream(),
            mybatisConfiguration,
            mapperLocation.toString(),
            mybatisConfiguration.getSqlFragments());

        xmlMapperBuilder.parse();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });

    var mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
    mybatisSqlSessionFactoryBean.setDataSource(druidDatasource);

    var mybatisSqlSessionFactory = mybatisSqlSessionFactoryBean.getObject();

    var sqlSession = Objects.requireNonNull(mybatisSqlSessionFactory).openSession();

    var orderDao = sqlSession.getMapper(OrderDao.class);
    var count = orderDao.queryCount(12L);
    logger.info("count->{}", count);

    var orderInfo = new MyOrder();
    orderInfo.setOrderId(RandomUtils.nextLong(100_0000L, 900_0000L));
    orderInfo.setUserId(333);
    orderInfo.setCreateTime(new Date());

    // orderDao.insert(orderInfo);

    sqlSession.commit();

    var list = orderDao.selectList(new QueryWrapper<>());
    list.forEach(myOrder ->
        logger.info("orderInfo:{}", JSON.toJSONString(myOrder)));

    sqlSession.close();
  }
}
