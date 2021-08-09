package test;

import static java.math.RoundingMode.UP;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/4/20-16:54
 */
public class BigDecimalTest {

  @Test
  public void test12() {

    var a = new BigDecimal("10");
    var b = new BigDecimal("3");
    // var c = a.divide(b, 5, DOWN);
    var c = a.divide(b, 5, UP);
    System.out.println("c = " + c);
  }
}
