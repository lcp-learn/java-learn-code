package com.lcp.learn.classloader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class ClassGetMain {

  private static final Logger logger = LoggerFactory.getLogger(ClassGetMain.class);

  public static void main(String[] args) {

    var set = getClassSet("com.lcp.learn.classloader.loader");
    var result = set.stream().filter(clazz -> {
      return clazz.getAnnotation(Component.class) != null;

      //            var annotations = clazz.getAnnotations();
      //            for (var annotation : annotations) {
      //                if (annotation.annotationType().getName().equals(Component.class.getName())) {
      //                    return true;
      //                }
      //            }
      //
      //            return false;
    }).collect(Collectors.toList());
    logger.info("set = {}", set);
    logger.info("result = {}", result);

  }

  /**
   * 获取类加载器
   *
   * @param @return 设定文件
   * @return ClassLoader    返回类型
   * @throws
   * @Title: getClassLoader
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static ClassLoader getClassLoader() {

    return Thread.currentThread().getContextClassLoader();
  }

  /**
   * 加载类 需要提供类名与是否初始化的标志， 初始化是指是否执行静态代码块
   *
   * @param @param  className
   * @param @param  isInitialized  为提高性能设置为false
   * @param @return 设定文件
   * @return Class<?>    返回类型
   * @throws
   * @Title: loadClass
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Class<?> loadClass(String className, boolean isInitialized) {

    Class<?> cls;
    try {
      cls = Class.forName(className, isInitialized, getClassLoader());
      //Thread.currentThread().getContextClassLoader().loadClass(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      logger.error("加载类失败 loadClass->{}", e);
      throw new RuntimeException(e);
    }

    return cls;
  }

  /**
   * 加载指定包下的所有类
   *
   * @param @param  packageName
   * @param @return 设定文件
   * @return Set<Class < ?>>    返回类型
   * @throws
   * @Title: getClassSet
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  public static Set<Class<?>> getClassSet(String packageName) {
    Set<Class<?>> classSet = new HashSet<>();

    try {
      Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

      while (urls.hasMoreElements()) {

        URL url = urls.nextElement();

        if (url != null) {

          String protocol = url.getProtocol();

          if (protocol.equals("file")) {
            // 转码
            String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
            // String packagePath =url.getPath().replaceAll("%20",
            // "");
            // 添加
            addClass(classSet, packagePath, packageName);

          } else if (protocol.equals("jar")) {

            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();

            if (jarURLConnection != null) {
              JarFile jarFile = jarURLConnection.getJarFile();

              if (jarFile != null) {

                Enumeration<JarEntry> jarEntries = jarFile.entries();

                while (jarEntries.hasMoreElements()) {

                  JarEntry jarEntry = jarEntries.nextElement();

                  String jarEntryName = jarEntry.getName();

                  if (jarEntryName.endsWith(".class")) {

                    String className =
                        jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll(
                            "/", ".");
                    doAddClass(classSet, className);

                  }
                }

              }
            }
          }

        }

      }

    } catch (IOException e) {
      e.printStackTrace();
      logger.error("查找包下的类失败", e);
    }

    return classSet;
  }

  /**
   * 添加文件到SET集合
   *
   * @param @param classSet
   * @param @param packagePath
   * @param @param packageName    设定文件
   * @return void    返回类型
   * @throws
   * @Title: addClass
   * @Description: TODO(这里用一句话描述这个方法的作用)
   */
  private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

    File[] files = new File(packagePath).listFiles(new FileFilter() {

      @Override
      public boolean accept(File file) {
        return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
      }
    });

    for (File file : files) {

      String fileName = file.getName();

      if (file.isFile()) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));

        if (StringUtils.isNotEmpty(packageName)) {

          className = packageName + "." + className;
          logger.error("className: {}", className);
        }
        // 添加
        doAddClass(classSet, className);
      } else {
        // 子目录
        String subPackagePath = fileName;
        if (StringUtils.isNotEmpty(packagePath)) {
          subPackagePath = packagePath + "/" + subPackagePath;
        }

        String subPackageName = fileName;
        if (StringUtils.isNotEmpty(packageName)) {
          subPackageName = packageName + "." + subPackageName;
        }

        addClass(classSet, subPackagePath, subPackageName);
      }
    }

  }

  private static void doAddClass(Set<Class<?>> classSet, String className) {

    Class<?> cls = loadClass(className, false);
    classSet.add(cls);
  }
}
