package org.lcp.java.example.concurrent.lock;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/11-17:20
 */
public class Counter {

  private int value;

  public Counter(int initValue) {
    this.value = initValue;
  }

  public int getValue() {
    return value;
  }

  public void add(int step) {
    value += step;
  }

}
