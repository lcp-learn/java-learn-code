package check;

import org.junit.jupiter.api.Test;
import org.lcp.learn.java.beans.Person;

public class BeanTest {

  @Test
  public void test11() {

    var clazz = Person.class;

    var fields = clazz.getDeclaredFields();

    System.out.println("fields = " + fields.length);

  }

  @Test
  public void test26() {

    byte state = 1;
    System.out.println("state = " + Integer.toBinaryString(state));
    state = 1 << 1;
    System.out.println("state = " + Integer.toBinaryString(state));
    state = 1 << 2;
    System.out.println("state = " + Integer.toBinaryString(state));

  }
}
