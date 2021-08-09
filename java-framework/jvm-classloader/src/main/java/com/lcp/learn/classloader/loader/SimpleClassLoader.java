package com.lcp.learn.classloader.loader;

import java.io.ByteArrayOutputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:  simple  <br/>
 *
 * @author lichunpeng
 * @since 2020/12/10-14:16
 */
public class SimpleClassLoader extends URLClassLoader {

  private static final String XIE_GANG = "/";//斜杠
  private static final String CLAZZ_SUB = ".class";
  private final Logger logger = LoggerFactory.getLogger(SimpleClassLoader.class);
  private String jarHttpUrl = "http://127.0.0.1:7896/action-impl-1.0-SNAPSHOT.jar";

  public SimpleClassLoader(URL[] urls) {
    super(urls);
  }

  public void init() {
    try {
      addURL(new URL(getJarHttpUrl()));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  String getJarHttpUrl() {
    return jarHttpUrl;
  }

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    try {

      var url = new URL("jar:" + getJarHttpUrl() + "!/");
      var jarURLConnection = (JarURLConnection) url.openConnection(); //得到地址
      var jarEntryEnumeration = jarURLConnection.getJarFile().entries();//得到集合

      var jarFile = jarURLConnection.getJarFile();

      var prefix = name.replace(".", XIE_GANG);

      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      while (jarEntryEnumeration.hasMoreElements()) {
        var jarEntry = jarEntryEnumeration.nextElement();
        var realName = jarEntry.getRealName();

        if (realName.endsWith(CLAZZ_SUB)
            && realName.contains(prefix)) {
          logger.info("realName = {}", realName);

          try {
            var inputStream = jarFile.getInputStream(jarEntry);

            var data = inputStream.read();

            while (data != -1) {
              buffer.write(data);
              data = inputStream.read();
            }

            inputStream.close();
            break;
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      }

      var classData = buffer.toByteArray();

      return defineClass(name, classData, 0, classData.length);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

}
