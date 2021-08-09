package com.lcp.learn.base.collections;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/25-09:50
 */
public class JodaTimeMain {

  private static final Logger logger = LoggerFactory.getLogger(JodaTimeMain.class);

  public static void main(String[] args) {
    var juDate = new Date();
    var dateTime = new DateTime(juDate);

    logger.info("dt:{}", dateTime.getMonthOfYear());

    var localTime = new LocalTime();
    logger.info("localTime:{}", localTime);

    var localDate = new LocalDate();
    logger.info("localDate:{}", localDate);

    var localDateTime = new LocalDateTime();
    logger.info("localDateTime:{}", localDateTime);

  }
}
