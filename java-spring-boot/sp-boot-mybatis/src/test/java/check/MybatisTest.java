package check;

import com.lcp.learn.spb.mybatis.MybatisPlusMain;
import com.lcp.learn.spb.mybatis.dao.MyUserDao;
import javax.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * desc:    <br/>
 * @since 2020/5/19-14:25 
 * @author lichunpeng
 */
@DisplayName("测试")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {MybatisPlusMain.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @LocalServerPort
    private int port;

    @Resource
    private MyUserDao myUserDao;

    @BeforeEach
    public void beforeTest() {
        logger.info("port:{}", port);
    }

    @Test
    @DisplayName("哈哈")
    public void test17() {

      // var user = myUserDao.selectById(1300);
      // logger.info("user:{}", JSON.toJSONString(user));
      //
        // var list = sqlSessionTemplate
        //         .selectList("com.lcp.learn.spb.mybatis.dao.MyUserDao.selectById", 13002);
        // logger.info("list.size:{}", list.size());

        String name = myUserDao.getNameById(1300);
        logger.info("name:{}", name);

        int age = myUserDao.getAgeById(1300);
        logger.info("age:{}", age);

        // sqlSessionTemplate.getConfiguration().getMappedStatementNames()
        //         .forEach(name -> logger.info("name:{}", name));
    }

}
