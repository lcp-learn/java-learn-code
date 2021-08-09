package com.lcp.learn.framework.seriali;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.lcp.learn.java.beans.Address;
import org.lcp.learn.java.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/14-13:36
 */
public class JacksonMain {

  private static final Logger logger = LoggerFactory.getLogger(JacksonMain.class);

  public static void main(String[] args) {

    var objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    var user = new User();
    user.setName("asdasd");
    user.setAge(120);
    user.setScore(450L);
    user.setAddress(new Address());
    user.setCheckoutTime(new Date());

    try {
      var result = objectMapper.writeValueAsString(user);
      logger.info("result:{}", result);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
