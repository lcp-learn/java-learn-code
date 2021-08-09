package com.lcp.learn.java.jvm.jol;

import org.lcp.learn.java.beans.User;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JolMain {

  private static final Logger logger = LoggerFactory.getLogger(JolMain.class);

  public static void main(String[] args) throws Exception {

    // http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/oops/markOop.hpp

    // https://stackoverflow.com/questions/60985782/details-about-mark-word-of-java-object-header

    //  32 bits:
    //  --------
    //             hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
    //             JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
    //             size:32 ------------------------------------------>| (CMS free block)
    //             PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
    //
    //  64 bits:
    //  --------
    //  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
    //  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
    //  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
    //  size:64 ----------------------------------------------------->| (CMS free block)
    //
    //  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
    //  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
    //  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
    //  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)

    //        f1();
    //        f2();
    f3();

  }

  private static void f3() {
    var o = new Object();
    synchronized (o) {
      var classLayer = ClassLayout.parseInstance(o);
      classLayer.fields().forEach(fieldLayout -> {
        System.out.println("classShortName = " + fieldLayout.classShortName());
        System.out.println("shortFieldName = " + fieldLayout.shortFieldName());
      });
      System.out.println(classLayer.toPrintable());
    }
  }

  private static void f2() throws Exception {
    Thread currentThread = Thread.currentThread();
    System.out.println("threadId : " + Long.toBinaryString(currentThread.getId()));
    Object o = new Object();
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");
    System.out.println("init object info");
    System.out.println(ClassLayout.parseInstance(o).toPrintable());
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");
    synchronized (o) {
      System.out.println("synchronized lock object info");
      System.out.println(ClassLayout.parseInstance(o).toPrintable());
      System.out.println("synchronized finished");
      System.out.println(
          "-------------------------------------------------------------------------------------------------------");
    }
    Thread.sleep(2000);
    System.out.println("after synchronized object info");
    System.out.println(ClassLayout.parseInstance(o).toPrintable());
    System.out.println("binary hashCode : " + Integer.toBinaryString(o.hashCode()));
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");

    System.out.println("after calculate hashcode object info");
    System.out.println(ClassLayout.parseInstance(o).toPrintable());
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");

    synchronized (o) {
      System.out.println("synchronized lock object info");
      System.out.println(ClassLayout.parseInstance(o).toPrintable());
      System.out.println("synchronized finished");
      System.out.println(
          "-------------------------------------------------------------------------------------------------------");
    }
    Object o2 = new Object();
    System.out.println("o2 hashCode : " + Long.toBinaryString(o2.hashCode()));
    System.out.println("init lock object2 info");
    System.out.println(ClassLayout.parseInstance(o2).toPrintable());
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");
    synchronized (o2) {
      System.out.println("synchronized lock object2 info");
      System.out.println(ClassLayout.parseInstance(o2).toPrintable());
      System.out.println("synchronized finished");
      System.out.println(
          "-------------------------------------------------------------------------------------------------------");
    }
    System.out.println("after lock object2 info");
    System.out.println(ClassLayout.parseInstance(o2).toPrintable());
    System.out.println(
        "-------------------------------------------------------------------------------------------------------");
    //计算过hashcode的会直接进入轻量锁
  }

  private static void f1() {
    var user = new User();
    user.hashCode();
    logger.info("{}", ClassLayout.parseInstance(user).toPrintable());
    var classLayout = ClassLayout.parseInstance(user);
    logger.info("classLayout.instanceSize:{}", classLayout.instanceSize());
    logger.info("classLayout.headerSize:{}", classLayout.headerSize());
    var virtualMachine = VM.current();
    logger.info("virtualMachine.objectHeaderSize:{}", virtualMachine.objectHeaderSize());
    logger.info("VM.current.details:{}", VM.current().details());
    logger.info("{}", ClassLayout.parseInstance(User.class).toPrintable());
  }
}
