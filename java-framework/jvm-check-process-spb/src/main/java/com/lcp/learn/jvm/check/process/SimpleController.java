package com.lcp.learn.jvm.check.process;

import com.lcp.learn.classloader.loader.SimpleClassLoader;
import java.net.URL;
import org.lcp.learn.java.api.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/8-16:01
 */
@RestController
@RequestMapping("/simple")
public class SimpleController {

  private static final String implsPackageName = "org.lcp.learn.java.actions.impls";
  private final Logger logger = LoggerFactory.getLogger(SimpleController.class);
  private UserAction userAction;
  private String fileUrl = "http://127.0.0.1:7896/beans-1.0-SNAPSHOT.jar";

  @RequestMapping("/check1")
  public String check1(@RequestParam("className") String
      className) {

    try {

      var implsClassFullPath = implsPackageName + "." + className;

      SimpleClassLoader simpleClassLoader = new SimpleClassLoader(new URL[]{});
      simpleClassLoader.init();

      var clazz = simpleClassLoader.findClass(implsClassFullPath);

      userAction = (UserAction) clazz.getDeclaredConstructor().newInstance();
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    var message = "version:" + userAction.getVersion();
    logger.info(message);

    return message;

  }

}
