package test;

import com.google.common.collect.Lists;
import com.lcp.learn.base.collections.utils.SnowFlake;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.StringUtils;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2018/7/4-11:50
 */
public class StringTest {

  @Test
  void test12() {

    String aa =
        "repository {0} hosts a collection of sample projects based on JUnit Jupiter and JUnit Vintage. " +
            "You’ll find ";

    String result = StringUtils.replace(aa, "{0}", "888");
    System.out.println("result = " + result);

  }

  @Test
  public void test26() {

    JdkIdGenerator jdkIdGenerator = new JdkIdGenerator();
    System.out.println("jdkIdGenerator = " + jdkIdGenerator.generateId().toString());
    System.out.println("jdkIdGenerator = " + jdkIdGenerator.generateId().toString());
    System.out.println("jdkIdGenerator = " + jdkIdGenerator.generateId().toString());
    System.out.println("jdkIdGenerator = " + jdkIdGenerator.generateId().toString());
  }

  @Test
  public void test37() {

    List<String> result = Lists.newArrayList();
  }

  @Test
  public void test46() {

    var aa = "https://ppt-image.moxueyuan.com/uploads_v1/e/259/925/e25925/2020/02/17/15819059103045/img-0.jpg";
    for (int i = 0; i < 55; i++) {
      aa = "https://ppt-image.moxueyuan.com/uploads_v1/e/259/925/e25925/2020/02/17/15819059103045/img-" + i +
          ".jpg";
      System.out.println(aa);
    }
  }

  @Test
  public void test56() {

    String sql = "INSERT INTO simple_order.my_user (name, age) VALUES ('张三', 23)";

    for (int i = 0; i < 20; i++) {

      var age = RandomUtils.nextInt(10, 65);
      var name =
          // RandomStringUtils.random(7, true, false)+ RandomUtils.nextInt(10, 65);
          getRandomChinese(2);

      System.out.println("INSERT INTO my_user (name, age) VALUES ('" + name + "', " + age + ");");
    }
  }

  @Test
  public void test75() {

    var sql = "INSERT INTO my_order (order_id, user_id)" + " VALUES (394643024886591488, 1312)";

    for (int i = 0; i < 5; i++) {
      long orderId = SnowFlake.nextId();
      System.out.println("INSERT INTO my_order (order_id, user_id)" + " VALUES (" + orderId + ", 1312)");
    }
    for (int i = 0; i < 3; i++) {
      long orderId = SnowFlake.nextId();
      System.out.println("INSERT INTO my_order (order_id, user_id)" + " VALUES (" + orderId + ", 1300)");
    }

  }

  private String getRandomChinese(int count) {
    //返回中文
    var string = new StringBuilder(2);
    for (int i = 0; i < count; i++) {
      string.append(new String(new char[]{(char) (new Random().nextInt(20902) + 19968)}));
    }
    return string.toString();
  }

  @Test
  public void test104() {

    var a = "-1";
    var b = Integer.parseInt(a);
    System.out.println("b = " + b);
  }

}
