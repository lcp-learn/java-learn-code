package com.lcp.learn.spring.spb.check.other.services.impls;

import com.alibaba.druid.pool.DruidDataSource;
import com.lcp.learn.spring.spb.check.other.services.PoolService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 * @since 2020/10/15-17:07
 * @author lichunpeng
 */
@Service
public class PoolServiceImpl implements PoolService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void druid() {


        try {
            //数据源配置
          DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3316/simple_order");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
            dataSource.setUsername("root");
            dataSource.setPassword("aaaaaa");

            //下面都是可选的配置
            // dataSource.setInitialSize(8);  //初始连接数，默认0
            // dataSource.setMaxActive(8);  //最大连接数，默认8
            // dataSource.setMinIdle(8);  //最小闲置数
            // dataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
            // dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
            // dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

            show(dataSource);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void dhcp() {

        try {
            //数据源配置
          BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3316/simple_order");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
            dataSource.setUsername("root");
            dataSource.setPassword("aaaaaa");

            //下面都是可选的配置
            dataSource.setInitialSize(8);  //初始连接数，默认0
            dataSource.setMaxTotal(8);  //最大连接数，默认8
            dataSource.setMinIdle(8);  //最小闲置数
            dataSource.setMaxWaitMillis(2000);  //获取连接的最大等待时间，单位毫秒
            dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
            dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

            show(dataSource);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void mybatis() {


        try {
            //数据源配置
          PooledDataSource dataSource = new PooledDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3316/simple_order");
            dataSource.setDriver("com.mysql.cj.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
            dataSource.setUsername("root");
            dataSource.setPassword("aaaaaa");

            //下面都是可选的配置
            // dataSource.setInitialSize(8);  //初始连接数，默认0
            // dataSource.setMaxTotal(8);  //最大连接数，默认8
            // dataSource.setMinIdle(8);  //最小闲置数
            // dataSource.setMaxWaitMillis(2000);  //获取连接的最大等待时间，单位毫秒
            // dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
            // dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

            show(dataSource);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    private void show(DataSource dataSource) {

        try {
          //获取连接
          Connection connection = dataSource.getConnection();

          //Statement接口
          Statement statement = connection.createStatement();
          String sql1 = "select name from my_user";
          ResultSet resultSet = statement.executeQuery(sql1);
          resultSet.next();
          String name = resultSet.getString(1);
          logger.info("name:{}", name);

          //PreparedStatement接口
          String sql2 = "select name from my_user";
          PreparedStatement preparedStatement = connection.prepareStatement(sql2);
          preparedStatement.execute();

          //关闭连接
          connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
