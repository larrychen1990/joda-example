package com.makotogroup.joda.custom;

import org.apache.log4j.Logger;

import sun.rmi.runtime.Log;

public class LoggerTest {
  private  static Logger log=Logger.getLogger(Log.class.getName());
//      Logger.getLogger(getClass());
  
  public static void main(String[] args) {
    LoggerTest t=new LoggerTest();
    t.log.fatal("this is fatal log.");
    t.log.error("this is error log.");
    t.log.warn("this is warn log.");
    t.log.info("this is info log.");
    t.log.debug("this is debug log.");
  }
}


//public class LoggerTest {
//  
//  private static Logger log = Logger.getLogger(LoggerTest.class);
//  
//  public static void main(String[] args) {
//    log.fatal("this is fatal log.");
//    log.error("this is error log.");
//    log.warn("this is warn log.");
//    log.info("this is info log.");
//    log.debug("this is debug log.");
//  }
//}
