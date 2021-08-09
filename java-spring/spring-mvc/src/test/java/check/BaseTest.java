package check;

import com.lcp.learn.spring.mvc.controller.WorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/6-15:59
 */
// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml",
    "classpath:springmvc-servlet.xml"
})
@WebAppConfiguration
public class BaseTest {

  @Autowired
  private WorldController worldController;

  @Autowired
  private Environment environment;

  @Test
  public void test28() {
    //
    // Assert.assertNotNull(worldController);
    // System.out.println("worldController = " + worldController);
    //
    // Assert.assertNotNull(environment);
    // System.out.println("environment = " + environment);
  }

}
