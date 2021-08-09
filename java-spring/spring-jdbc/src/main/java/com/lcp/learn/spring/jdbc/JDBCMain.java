package com.lcp.learn.spring.jdbc;

import com.lcp.learn.spring.jdbc.utils.SnowFlake;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/10/10-11:49
 */
public class JDBCMain {

  private static final Logger logger = LoggerFactory.getLogger(JDBCMain.class);

  public static void main(String[] args) throws SQLException {

    AnnotationConfigApplicationContext applicationcontext =
        new AnnotationConfigApplicationContext("com.lcp.learn.spring.jdbc.config");

    JdbcTemplate jdbcTemplate = applicationcontext.getBean(JdbcTemplate.class);
    DataSourceTransactionManager dataSourceTransactionManager =
        applicationcontext.getBean(DataSourceTransactionManager.class);
    TransactionTemplate transactionTemplate = applicationcontext.getBean(TransactionTemplate.class);

    insertData2(jdbcTemplate, transactionTemplate);
    // insertData(jdbcTemplate, dataSourceTransactionManager);
    // selectData(jdbcTemplate);

  }

  private static void insertData2(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) throws SQLException {

    transactionTemplate.executeWithoutResult(transactionStatus -> {

      String addSql = "INSERT INTO my_user ( name, age) VALUES ( ?, ?)";
      int count1 = jdbcTemplate.update(addSql, getRandomChinese(2), RandomUtils.nextInt(10, 99));
      logger.info("count add user:{}", count1);

      String addOrderSql = "INSERT INTO my_order ( order_id, user_id, create_time) VALUES ( ?, ?, now())";
      int count2 = jdbcTemplate.update(addOrderSql, SnowFlake.nextId(), 1300);
      logger.info("count add order:{}", count2);
    });

  }

  private static void insertData(JdbcTemplate jdbcTemplate,
      DataSourceTransactionManager dataSourceTransactionManager) throws SQLException {

    DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);

    try {

      String addSql = "INSERT INTO my_user ( name, age) VALUES ( ?, ?);";
      int count = jdbcTemplate
          .update(addSql, getRandomChinese(2), RandomUtils.nextInt(10, 99));
      logger.info("count add user:{}", count);

      String addOrderSql = "INSERT INTO my_order ( order_id, user_id, create_time) VALUES ( ?, ?, now())";
      count = jdbcTemplate
          .update(addOrderSql, SnowFlake.nextId(), "1300xx");
      logger.info("count add order:{}", count);

      dataSourceTransactionManager.commit(transactionStatus);

    } catch (Exception exception) {
      // exception.printStackTrace();
      dataSourceTransactionManager.rollback(transactionStatus);
    }

  }

  private static void selectData(JdbcTemplate jdbcTemplate) {

    String selectSql = "select id,name from my_user where id > ?";

    List<Map<String, Object>> result = jdbcTemplate.queryForList(selectSql, 1300);

    result.stream().map(line -> {
      StringBuilder desc = new StringBuilder();
      line.keySet().forEach(k -> desc
          .append(k)
          .append(":")
          .append(line.get(k))
          .append(","));

      return desc.toString();
    }).forEach(desc -> logger.info("line:\t{}", desc));
  }

  /**
   * 生成中文
   *
   * @param count
   * @return
   */
  private static String getRandomChinese(int count) {

    //返回中文
    StringBuilder string = new StringBuilder(count);
    for (int i = 0; i < count; i++) {
      string.append(new String(new char[]{(char) (new Random().nextInt(20902) + 19968)}));
    }
    return string.toString();
  }

  public static char getRandomChar() {
    return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
  }

}
