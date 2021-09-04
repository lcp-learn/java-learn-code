package check;

import com.lcp.learn.spring.spb.check.other.SpbCheckMain;
import com.lcp.learn.spring.spb.check.other.services.LockService;
import com.lcp.learn.spring.spb.check.other.services.PoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        classes = {SpbCheckMain.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @LocalServerPort
    private int port;

    @Autowired
    private LockService lockService;

    @Autowired
    private PoolService poolService;

    @BeforeEach
    public void beforeTest() {
        logger.info("port:{}", port);
    }

    @Test
    @DisplayName("redisson加锁")
    public void test17() {

        for (int i = 0; i < 10; i++) {
            int result = lockService.updateData(i, 456);
            logger.info("result:{}", result);
        }


    }

    @Test
    @DisplayName("db连接池")
    public void testPoolService() {

        poolService.dhcp();
        poolService.druid();
        poolService.mybatis();

    }
}
