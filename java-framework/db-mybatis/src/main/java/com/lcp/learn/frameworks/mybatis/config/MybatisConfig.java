package com.lcp.learn.frameworks.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-15:21
 */
@Configuration
public class MybatisConfig {

  @Bean
  public DataSource buildDatasource() {

    var druidDatasource = new DruidDataSource();
    druidDatasource.setUsername("root");
    druidDatasource.setPassword("aaaaaa");
    druidDatasource.setUrl("jdbc:mysql://127.0.0.1:3316/simple_order");
    druidDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return druidDatasource;
  }

  @Bean("sqlSessionFactory1")
  public SqlSessionFactoryBean buildSqlSessionFactoryBean(DataSource dataSource) throws Exception {

    var resolver =
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");

    var sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setMapperLocations(resolver);
    sqlSessionFactoryBean.setDataSource(dataSource);
    return sqlSessionFactoryBean;
  }

  @Bean
  public MapperScannerConfigurer buildMapperScannerConfigurer() {
    var mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setBasePackage("com.lcp.learn.frameworks.mybatis.dao");
    return mapperScannerConfigurer;
  }
}
