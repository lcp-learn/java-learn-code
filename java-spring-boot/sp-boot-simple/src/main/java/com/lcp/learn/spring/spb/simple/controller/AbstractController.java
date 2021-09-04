package com.lcp.learn.spring.spb.simple.controller;

import com.lcp.learn.spring.spb.simple.beans.response.ResponseInfo;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-14:56
 */
public abstract class AbstractController {

  protected <E> ResponseInfo<E> getSuccessResponse(E resultData) {
    return new ResponseInfo<>(0, "SUCCESS", resultData);
  }

}
