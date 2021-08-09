package com.lcp.learn.spring.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/10-12:08
 */
@Configuration
@PropertySource("classpath:db.properties")
public class JdbcConfig {

  @Value("${db.url}")
  private String dbUrl;

  @Value("${db.username}")
  private String dbName;

  @Value("${db.password}")
  private String dbPassword;

  @Bean("druidDataSource")
  public DataSource buildDatasource() {

    //创建数据源连接池
    DruidDataSource dataSource = new DruidDataSource();

    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl(dbUrl);
    dataSource.setUsername(dbName);
    dataSource.setPassword(dbPassword);

    return dataSource;
  }

  @Bean("simpleJdbcTemplate")
  public JdbcTemplate buildJdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }

  @Bean("simpleTransactionManager")
  public DataSourceTransactionManager buildTransactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    dataSourceTransactionManager.setRollbackOnCommitFailure(true);
    return dataSourceTransactionManager;
  }

  @Bean("simpleTransactionTemplate")
  public TransactionTemplate buildTransactionTemplate(DataSourceTransactionManager transactionManager) {
    TransactionTemplate transactionTemplate = new TransactionTemplate();
    transactionTemplate.setTransactionManager(transactionManager);
    return transactionTemplate;
  }
}
