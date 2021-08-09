package com.lcp.learn.java.proxy.cglib.impl;

import com.lcp.learn.java.proxy.api.Action;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-09:23
 */
public class CGlibActionImpl implements Action {

  @Override
  public void sayHello() {
    System.out.println("CGlibActionImpl sayHello");
  }

  @Override
  public void sayBye() {
    System.out.println("CGlibActionImpl sayBye");
  }
}
