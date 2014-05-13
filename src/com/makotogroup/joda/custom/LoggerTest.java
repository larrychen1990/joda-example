package com.makotogroup.joda.custom;

import org.apache.log4j.Logger;


public class LoggerTest {
  private static Logger log=Logger.getLogger(LoggerTest.class);
  
  public static void main(String[] args) {
    log.fatal("this is fatal log.");
    log.error("this is error log.");
    log.warn("this is warn log.");
    log.info("this is info log.");
    log.debug("this is debug log.");
  }
}
