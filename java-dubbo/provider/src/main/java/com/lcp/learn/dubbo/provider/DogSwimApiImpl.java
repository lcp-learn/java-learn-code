package com.lcp.learn.dubbo.provider;

import com.lcp.learn.dubbo.api.SwimApi;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/12-15:16
 */
@DubboService
public class DogSwimApiImpl implements SwimApi {

  @Override
  public String doSwim(String name) {
    return "dog swim ...";
  }
}
