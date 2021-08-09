package org.lcp.java.example.concurrent.threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/2-17:53
 */
public class ThreadLocalMemory {

  public static final int COUNT = 10_000;

  /**
   * Thread local variable containing each thread's ID
   */
  public ThreadLocal<List<Object>> threadId = ThreadLocal.withInitial(() -> {
    var list = new ArrayList<>();
    for (int i = 0; i < COUNT; i++) {
      list.add(String.valueOf(i));
    }
    return list;
  });

  /**
   * Returns the current thread's unique ID, assigning it if necessary
   *
   * @return
   */
  public List<Object> get() {
    return threadId.get();
  }

  /**
   * remove currentid
   */
  public void remove() {
    threadId.remove();
  }

}
