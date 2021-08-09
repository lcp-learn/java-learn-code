package com.lcp.learn.spring.hibernate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQLDialect;
import org.mariadb.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-13:22
 */
@Configuration
@Transactional
public class HibernateConfig {

  @Bean
  public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {

    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    comboPooledDataSource.setUser("root");
    comboPooledDataSource.setPassword("aaaaaa");
    comboPooledDataSource.setJdbcUrl("jdbc:mariadb://127.0.0.1:3906/check1");
    comboPooledDataSource.setDriverClass(Driver.class.getName());
    return comboPooledDataSource;

  }

  @Bean
  public LocalSessionFactoryBean localSessionFactoryBean(ComboPooledDataSource comboPooledDataSource) {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

    Properties properties = new Properties();
    properties.put("hibernate.dialect", MySQLDialect.class.getName());
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.put("hibernate.show_sql", true);
    properties.put("hibernate.format_sql", false);
    localSessionFactoryBean.setHibernateProperties(properties);
    localSessionFactoryBean.setDataSource(comboPooledDataSource);
    localSessionFactoryBean.setPackagesToScan("");
    return localSessionFactoryBean;
  }

  @Bean
  public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
    hibernateTransactionManager.setSessionFactory(sessionFactory);
    return hibernateTransactionManager;
  }
}
