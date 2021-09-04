package check;

import com.lcp.learn.spring.spb.check.other.SpbCheckMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * desc:    <br/>
 * @since 2020/10/20-18:22
 * @author lichunpeng
 */
@DisplayName("测试")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {SpbCheckMain.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedssionTest {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @LocalServerPort
    private int port;

    @Autowired
    private RedissonClient redissonClient;

    @BeforeEach
    public void beforeTest() {
        logger.info("port:{}", port);
    }

    @Test
    @DisplayName("redisson-simple")
    public void test17() {

        Assertions.assertNotNull(redissonClient);

        // RBucket<String> rBucket = redissonClient.getBucket("name");
        // rBucket.set("lcphahah", 60L, TimeUnit.MINUTES);
        // var result = rBucket.get();
        // logger.info("result:{}", result);

        // var user = new User();
        // user.setName("zhangsan");
        // user.setAge(44);
        // user.setAddress(new Address());
        // RBucket<User> firstUser = redissonClient.getBucket("firstUser");
        // firstUser.set(user);

        // RMap<String, Integer> top10 = redissonClient.getMap("top10");
        // top10.put("zhangsan", 345);
        // top10.put("lisi", 331);
        // var result = top10.get("zhangsan");
        // logger.info("result:{}", result);

        RAtomicLong count = redissonClient.getAtomicLong("count");
        logger.info("count.get():{}", count.get());
        // count.incrementAndGet();
        // count.incrementAndGet();
        // count.incrementAndGet();
        // count.incrementAndGet();

    }


}
