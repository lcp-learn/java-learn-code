package com.lcp.learn.servlet.my.servlet.container.processer;

import com.lcp.learn.servlet.my.servlet.container.Request;
import com.lcp.learn.servlet.my.servlet.container.Response;
import java.io.IOException;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/5-16:40
 */
public class StaticResourceProcessor {

  public void process(Request request, Response response) {
    try {
      response.sendStaticResource();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
