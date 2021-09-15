package com.lcp.learn.dubbo.provider;

import com.lcp.learn.dubbo.api.CallbackApi;
import com.lcp.learn.dubbo.api.callable.MyCallbackListener;
import org.apache.dubbo.config.annotation.DubboService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/9-11:54
 */
@DubboService
public class SimpleCallbackApiImpl implements CallbackApi {


  private final Map<String, MyCallbackListener> listeners = new ConcurrentHashMap<>();

  public SimpleCallbackApiImpl() {
    Thread thread = new Thread(() -> {
      while (true) {
        try {
          for (Map.Entry<String, MyCallbackListener> entry : listeners.entrySet()) {
            try {
              entry.getValue().changed(getChanged(entry.getKey()));
            } catch (Throwable t1) {
              listeners.remove(entry.getKey());
            }
          }
          Thread.sleep(5000); // 定时触发变更通知
        } catch (Throwable t1) { // 防御容错
          t1.printStackTrace();
        }
      }
    });
    thread.setDaemon(true);
    thread.start();
  }

  @Override
  public void addListener(String key, MyCallbackListener myCallbackListener) {
    listeners.put(key, myCallbackListener);
    myCallbackListener.changed(getChanged(key)); // 发送变更通知
  }

  private String getChanged(String key) {
    return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

}
