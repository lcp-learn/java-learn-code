package com.lcp.learn.base.collections;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeMain {

  private static final Logger logger = LoggerFactory.getLogger(DateTimeMain.class);

  public static void main(String[] args) {

    var clock = Clock.systemDefaultZone();
    var millis = clock.millis();

    var pattern = "yyyy-MM-dd HH:mm:ss";

    var result = DateFormatUtils.format(millis, pattern);
    logger.info("result:{}", result);

    var today = LocalDate.now();
    var tomorrow = today.plus(1, ChronoUnit.DAYS);
    var yesterday = tomorrow.minusDays(2);

    var independenceDay = LocalDate.of(2020, Month.JULY, 4);
    var dayOfWeek = independenceDay.getDayOfWeek();

    logger.info("dayOfWeek:{} dayOfWeek.value:{}", dayOfWeek, dayOfWeek.getValue());    // FRIDAY
    logger.info("today = {}", today.getDayOfWeek());

    var start = Instant.parse("2020-08-03T10:15:30.00Z");
    var end = Instant.parse("2020-08-03T10:16:30.12Z");
    var duration = Duration.between(start, end);
    logger.info("{}", duration.getSeconds());
    logger.info("{}", duration.getNano());
    logger.info("{}", duration.getUnits());

  }
}
