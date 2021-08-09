package com.lcp.learn.classloader;

import com.lcp.learn.classloader.loader.ImplClassLoader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import org.lcp.learn.java.api.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-15:05
 */
public class ClassLoaderMain {

  private static final Logger logger = LoggerFactory.getLogger(ClassLoaderMain.class);

  public static void main(String[] args) throws Exception {

    var fileUrl = "http://127.0.0.1:7896/beans-1.0-SNAPSHOT.jar";

    // var packageName = "org.lcp.learn.java.beans";
    // var classFullPath = packageName + ".User";
    // loadClazz(fileUrl, classFullPath, packageName);

    var interfacesPackageName = "org.lcp.learn.java.beans.api";
    var implsPackageName = "org.lcp.learn.java.beans.impls";
    var implsClassFullPath = implsPackageName + ".UserActionImpl";
    var action = loadClazz2(fileUrl, implsClassFullPath);
    var result = action.doHaha("张三");
    logger.info("result:{}", result);

  }

  private static UserAction loadClazz2(String fileUrl, String classFullPath) throws Exception {

    var implClassLoader = new ImplClassLoader(new URL[]{}, fileUrl);
    implClassLoader.init();

    var clazz = implClassLoader.loadClass(classFullPath);

    return (UserAction) clazz.getDeclaredConstructor().newInstance();
  }

  private static void loadClazz(String fileUrl, String classFullPath, String packageName) throws Exception {

    var implClassLoader = new ImplClassLoader(new URL[]{}, fileUrl);
    implClassLoader.init();

    implClassLoader.getClassNameList(packageName).forEach(clazzName -> logger.info("clazz name:{}", clazzName));

    var clazz = implClassLoader.loadClass(classFullPath);
    logger.info("clazz = {}", clazz.getName());
    logger.info("clazz.classloader = {}", clazz.getClassLoader().getName());
    Method[] methods = clazz.getMethods();
    for (Method method : methods) {
      logger.info("\tmethod = {}", method.getName());
      Parameter[] parameters = method.getParameters();
      for (Parameter parameter : parameters) {
        logger.info("\t\tparameter:{}, type:{}", parameter.getName(), parameter.getType().getName());
      }
    }

    // Class<?> implClazz = implClassLoader.loadClass("com.lcp.learn.basic.api.impls.UserApiImpl");
    // logger.info("implClazz = " + implClazz.getName());
    //
    // Class<?>[] interfaces = implClazz.getInterfaces();
    // for (Class interfaceInstance : interfaces) {
    //   logger.info("interfaceInstance = " + interfaceInstance.getName());
    // }
    //
    // try {
    //   Object obj = implClazz.getDeclaredConstructor().newInstance();
    //   logger.info("obj.toString() = " + obj.toString());
    //   //                    Method method = aClass.getMethod("show");
    //   //                    method.invoke(obj);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
  }

}
