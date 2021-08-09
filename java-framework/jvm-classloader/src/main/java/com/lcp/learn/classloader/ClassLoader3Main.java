package com.lcp.learn.classloader;

import com.lcp.learn.classloader.loader.ImplClassLoader;
import com.lcp.learn.classloader.loader.SimpleClassLoader;
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
public class ClassLoader3Main {

  private static final Logger logger = LoggerFactory.getLogger(ClassLoader3Main.class);

  public static void main(String[] args) throws Exception {

    var implsPackageName = "org.lcp.learn.java.beans.impls";
    var implsClassFullPath = implsPackageName + ".UserActionV2Impl";

    var simpleClassLoader = new SimpleClassLoader(new URL[]{});
    var clazz = simpleClassLoader.loadClass(implsClassFullPath);

    logger.info("result:{}", clazz);

  }

  private static UserAction loadClazz2(String fileUrl, String classFullPath) throws Exception {

    var implClassLoader = new ImplClassLoader(new URL[]{}, fileUrl);
    implClassLoader.init();

    var clazz = implClassLoader.loadClass(classFullPath);

    return (UserAction) clazz.getDeclaredConstructor().newInstance();
  }

}
