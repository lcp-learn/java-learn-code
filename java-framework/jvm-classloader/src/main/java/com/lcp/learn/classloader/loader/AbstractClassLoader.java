package com.lcp.learn.classloader.loader;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-17:59
 */
public abstract class AbstractClassLoader extends URLClassLoader {

  private static final String XIE_GANG = "/";//斜杠
  private static final String CLAZZ_SUB = ".class";
  private final Logger logger = LoggerFactory.getLogger(AbstractClassLoader.class);

  AbstractClassLoader(URL[] urls) {
    super(urls);
  }

  /**
   * 得到jar地址
   *
   * @return
   */
  abstract String getJarHttpUrl();

  public void init() {
    try {
      addURL(new URL(getJarHttpUrl()));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    try {

      var url = new URL("jar:" + getJarHttpUrl() + "!/");
      var jarURLConnection = (JarURLConnection) url.openConnection(); //得到地址
      var jarEntryEnumeration = jarURLConnection.getJarFile().entries();//得到集合

      var jarFile = jarURLConnection.getJarFile();

      var prefix = name.replace(".", XIE_GANG);

      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      while (jarEntryEnumeration.hasMoreElements()) {
        var jarEntry = jarEntryEnumeration.nextElement();
        String realName = jarEntry.getRealName();

        if (realName.endsWith(CLAZZ_SUB)
            && realName.contains(prefix)) {

          logger.info("realName = {}", realName);

          try {
            var inputStream = jarFile.getInputStream(jarEntry);

            int data = inputStream.read();

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

      byte[] classData = buffer.toByteArray();

      return defineClass(name, classData, 0, classData.length);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

  /**
   * 遍历jar,得到指定的class名字
   *
   * @param packageName 指定的包名
   * @throws IOException
   */
  public List<String> getClassNameList(String packageName) throws IOException {

    var url = new URL("jar:" + getJarHttpUrl() + "!/");
    var jarURLConnection = (JarURLConnection) url.openConnection(); //得到地址
    var jarEntryEnumeration = jarURLConnection.getJarFile().entries();//得到集合

    var prefix = packageName.replace(".", XIE_GANG);

    var clazzNameList = new LinkedList<String>();

    jarEntryEnumeration.asIterator().forEachRemaining(jarEntry -> { //遍历
      String realName = jarEntry.getRealName();
      if (realName.endsWith(CLAZZ_SUB) && realName.startsWith(prefix)
          && realName.lastIndexOf("/") == prefix.length()) {

        clazzNameList.add(
            realName.replace(XIE_GANG, ".").substring(INTEGER_ZERO, realName.indexOf(CLAZZ_SUB)));

      }
    });
    return clazzNameList;
  }

  public List<String> getClassNameList(String httpurl, int a) throws IOException {

    URL url = new URL("jar:" + httpurl + "!/");
    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection(); //得到地址
    Enumeration<JarEntry> entrys = jarURLConnection.getJarFile().entries();//得到集合

    List<String> clazzNameList = new LinkedList<>();

    entrys.asIterator().forEachRemaining(jarEntry -> { //遍历
      String realName = jarEntry.getRealName();
      if (realName.endsWith(CLAZZ_SUB)) {

        clazzNameList.add(
            realName.replace(XIE_GANG, ".").substring(INTEGER_ZERO, realName.indexOf(CLAZZ_SUB)));

      }
    });
    return clazzNameList;
  }

}
