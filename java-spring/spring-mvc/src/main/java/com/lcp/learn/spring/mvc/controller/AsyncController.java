package com.lcp.learn.spring.mvc.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/3-10:12
 */
@RestController
public class AsyncController {

  @RequestMapping("/async")
  public WebAsyncTask<String> call(@RequestParam(name = "name", required = false, defaultValue = "haha") String name) {
    long timeout = 5 * 1000L;
    WebAsyncTask<String> asyncTask = new WebAsyncTask<>(timeout, () -> {
      //            TimeUnit.MILLISECONDS.sleep(timeout + 10);
      return "async_request_callable";
    });
    asyncTask.onTimeout(() -> {
      System.out.println("响应超时回调");
      return "async_request_callable_timeout";
    });
    asyncTask.onCompletion(() -> System.out.println("响应callable调用完成的回调"));

    return asyncTask;
  }

  @RequestMapping("/callable")
  public Callable<String> forCallable(Model model) throws Exception {
    return () -> {
      TimeUnit.SECONDS.sleep(1);//睡眠1秒，模仿某些业务操作
      model.addAttribute("a", "aaaaaaa");
      return "callable_async_request_callable";
    };
  }

  @RequestMapping("/deferredresult")
  public DeferredResult<String> forDeferredResult() throws Exception {

    DeferredResult<String> result = new DeferredResult<>(10 * 1000L);
    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(31);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      result.setResult("deferredresult_async_request_deferredresult");
    }).start();

    result.onTimeout(() -> System.out.println("响应超时回调函数"));
    result.onCompletion(() -> System.out.println("响应完成的回调函数"));

    return result;
  }

}
