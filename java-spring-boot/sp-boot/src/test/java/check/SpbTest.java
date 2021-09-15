package check;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.SpbMain;
import com.lcp.learn.spring.spb.beans.User;
import com.lcp.learn.spring.spb.controller.SimpleController;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/5/19-14:25
 */
@DisplayName("测试")
// @ExtendWith(SpringExtension.class) //导入spring测试框架
@SpringBootTest(classes = {SpbMain.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //mockMvc
@RunWith(SpringRunner.class)
public class SpbTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private SimpleController simpleController;

  @Test
  @DisplayName("哈哈")
  public void test17() {
    System.out.println("log = ");
    Assert.assertNotNull(simpleController);
    User user = simpleController.hello(new User("lcp", 22), 12);
    System.out.println("user = " + JSON.toJSONString(user));
  }
}
