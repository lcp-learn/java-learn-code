package com.lcp.learn.dubbo.api;

import com.lcp.learn.dubbo.api.callable.MyCallbackListener;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/9-11:52
 */
public interface CallbackApi {

  void addListener(String key, MyCallbackListener myCallbackListener);

}
