package com.lcp.learn.base.hessian;

import java.io.IOException;
import org.lcp.learn.java.beans.Student;

public class HessianMain {

  public static void main(String[] args) throws IOException {

    System.out.println("args = " + args);

    var hessainUtil = new HessianUtil();

    var user = new Student();
    //        user.setName(null);

    byte[] data = hessainUtil.serialize(user);
    System.out.println("data = " + data.length);

  }
}
