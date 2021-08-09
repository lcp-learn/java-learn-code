package com.lcp.learn.servlet.web.simple.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/2-13:54
 */
public abstract class AbstractFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    handle((HttpServletRequest) request, (HttpServletResponse) response);
    response.setCharacterEncoding("UTF-8");
    chain.doFilter(request, response);
  }

  protected abstract void handle(HttpServletRequest request, HttpServletResponse response);
}
