package org.lcp.java.example.concurrent.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019-07-11-13:36
 */
public class ThreadMain {

  private static final Logger logger = LoggerFactory.getLogger(ThreadMain.class);

  int result;

  public static void main(String[] args) {

    // Thread t = new Thread(() -> {
    //   try {
    //     System.out.println("Begin ThreadImp");
    //     Thread.sleep(5000);//休息5s
    //     System.out.println("End ThreadImp");
    //   } catch (Exception e) {
    //     System.out.println(e);
    //   }
    // });
    //
    // t.start();
    // try {
    //   t.join(1000);//主程序等待t结束,只等1s
    //   if (t.isAlive()) {
    //     System.out.println("t has not finished");
    //   } else {
    //     System.out.println("t has finished");
    //   }
    //   System.out.println("Joinfinished");
    // } catch (Exception e) {
    //   System.out.println(e);
    // }
    new Thread(ThreadMain::func1).start();
    new Thread(ThreadMain::func1).start();
    new Thread(ThreadMain::func1).start();

    // 线程状态
    // 1、NEW,未启动的。不会出现在Dump中。
    // 2、RUNNABLE,在虚拟机内执行的。
    // 3、BLOCKED,受阻塞并等待监视器锁。
    // 4、WATING,无限期等待另一个线程执行特定操作。
    // 5、TIMED_WATING,有时限的等待另一个线程的特定操作。
    // 6、TERMINATED,已退出的。

    // 线程状态产生的原因：
    // 1、runnable：状态一般为RUNNABLE，表示线程具备所有运行条件，在运行队列中准备操作系统的调度，或者正在运行。
    // 2、in Object.wait()：等待区等待，状态为WAITING或TIMED_WAITING。
    // 3、waiting for monitor entry：进入区等待，状态为BLOCKED。
    // 4、waiting on condition：等待区等待，被park。
    // 5、sleeping：休眠的线程，调用了Thread.sleep()。

  }

  private static synchronized void func1() {

    try {
      logger.info("tname:{}", Thread.currentThread().getName());
      Thread.sleep(Integer.MAX_VALUE);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
