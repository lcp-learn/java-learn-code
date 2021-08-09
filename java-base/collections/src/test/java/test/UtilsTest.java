package test;

import com.google.common.collect.Lists;
import com.lcp.learn.base.collections.utils.SnowFlakeGenerator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2018/7/6-15:12
 */
public class UtilsTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void test12() {
    SnowFlakeGenerator.Factory factory = new SnowFlakeGenerator.Factory();
    SnowFlakeGenerator snowFlakeGenerator = factory.create(1, 1);
    for (int i = 0; i < 3; i++) {
      logger.info("{}", snowFlakeGenerator.nextId());
    }

    int a = 99;
    int b = 99;
    int result = CompareToBuilder.reflectionCompare(a, b);
    logger.info("result = {}", result);
  }

  @Test
  public void test12111() {
    List<Integer> aa = Lists.newLinkedList();
    aa.add(11);
    aa.add(12);
    aa.add(13);
    aa.add(14);
    aa.add(15);

    logger.info("aa = {}", aa.subList(0, 3));
    logger.info("aa = {}", aa.subList(3, 5));
  }

  @Test
  public void test57() {

    var value = StringUtils.capitalize("qwert");
    logger.info("value = {}", value);
  }

  @Test
  public void test66() {

    var snowFlakeGenerator = new SnowFlakeGenerator.Factory().create(179001L, 340023L);

    for (int i = 0; i < 10; i++) {

      var id = snowFlakeGenerator.nextId();
      logger.info("id:\t{}", id);
    }

  }

}
