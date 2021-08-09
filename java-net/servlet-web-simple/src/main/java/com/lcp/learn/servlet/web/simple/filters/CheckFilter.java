package com.lcp.learn.servlet.web.simple.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-13:46
 */
public class CheckFilter extends AbstractFilter {

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("check request ==================================== " + request);
  }
}
