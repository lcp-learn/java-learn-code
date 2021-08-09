package com.lcp.learn.classloader.loader;

import java.net.URL;
import org.springframework.stereotype.Component;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/19-15:06
 */
@Component
public class ImplClassLoader extends AbstractClassLoader {

  private String jarHttpUrl;

  public ImplClassLoader(URL[] urls, String jarHttpUrl) {
    super(urls);
    this.jarHttpUrl = jarHttpUrl;
  }

  @Override
  String getJarHttpUrl() {
    return jarHttpUrl;
  }

}
