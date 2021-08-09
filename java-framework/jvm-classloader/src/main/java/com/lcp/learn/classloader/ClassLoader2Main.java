package com.lcp.learn.classloader;

import com.lcp.learn.classloader.loader.ImplClassLoader;
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
public class ClassLoader2Main {

  private static final Logger logger = LoggerFactory.getLogger(ClassLoader2Main.class);

  public static void main(String[] args) throws Exception {

    var fileUrl = "http://127.0.0.1:7896/beans-1.0-SNAPSHOT.jar";

    var implsPackageName = "org.lcp.learn.java.beans.impls";
    var implsClassFullPath = implsPackageName + ".UserActionV2Impl";

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

}
