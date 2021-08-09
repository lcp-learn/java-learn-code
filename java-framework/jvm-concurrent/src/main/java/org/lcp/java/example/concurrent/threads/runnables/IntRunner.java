package org.lcp.java.example.concurrent.threads.runnables;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-10-11:12
 */
public class IntRunner implements Runnable {

  private Integer a;

  public IntRunner(Integer a) {
    this.a = a;
  }

  @Override
  public void run() {
    a = a + 1;
  }
}
