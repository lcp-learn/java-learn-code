package com.lcp.learn.spring.classloader;

import com.lcp.learn.spring.classloader.api.UserApi;
import com.lcp.learn.spring.classloader.beans.RequestInfo;
import com.lcp.learn.spring.classloader.manager.InstanceManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/20-12:44
 */
public class ClassLoaderMain {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    //        InstanceManager instanceManager = new InstanceManagerImpl();
    //        Object instance = instanceManager.getInstance("com.lcp.learn.spring.classloader.api.UserApi");
    //        List<RequestInfo> requestInfoList = new LinkedList<>();

    RequestInfo requestInfo = new RequestInfo();
    requestInfo.setPath("/a");
    requestInfo.setMethod("hello");
    requestInfo.setParam(new Object[]{"name"});
    requestInfo.setApiClazz(UserApi.class);

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext("com.lcp.learn.spring.classloader");

    InstanceManager instanceManager = applicationContext.getBean(InstanceManager.class);
    Class apiClass = requestInfo.getApiClazz();
    Method method = apiClass.getMethod(requestInfo.getMethod(), String.class);
    Object instance = instanceManager.getInstance(apiClass);
    Object result = method.invoke(instance, requestInfo.getParam());
    System.out.println("result = " + result);

  }
}
