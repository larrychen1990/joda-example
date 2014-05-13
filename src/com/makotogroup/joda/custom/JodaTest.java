package com.makotogroup.joda.custom;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.ISODateTimeFormat;

public class JodaTest {

  public static boolean isLeap(DateTime dateTime) {
    return dateTime.year().isLeap();
  }

  public static String getFullTime(DateTime dateTime) {
    return String.format("Centry %s %s-%s-%s %s:%s:%s.%s Week %s %s",
        dateTime.getCenturyOfEra() + 1, dateTime.getYear(),
        dateTime.getMonthOfYear(), dateTime.getDayOfMonth(),
        dateTime.getHourOfDay(), dateTime.getMinuteOfHour(),
        dateTime.getSecondOfMinute(), dateTime.getMillisOfSecond(),
        dateTime.getDayOfWeek(), dateTime.getZone());
  }

  // pay day is 26 or 28
  public static boolean isAfterPayDay(DateTime datetime) {
    if (datetime.getMonthOfYear() == 2) { // February is month 2!!
      return datetime.getDayOfMonth() > 26;
    }
    return datetime.getDayOfMonth() > 28;
  }

  public static Days daysToNewYear(LocalDate fromDate) {
    LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
    return Days.daysBetween(fromDate, newYear);
  }

  public static boolean isRentalOverdue(DateTime datetimeRented) {
    Period rentalPeriod = new Period().withDays(2).withHours(12);
    return datetimeRented.plus(rentalPeriod).isBeforeNow();
  }

  public static String getBirthMonthText(LocalDate dateOfBirth) {
    return dateOfBirth.monthOfYear().getAsText(Locale.ENGLISH);
  }

  public static void main(String[] args) {
    // construct a datetime use the system time
    System.out
        .println("====================construct a datetime use the system time===========================");
    DateTime dateTime = new DateTime();

    System.out.println(dateTime);
    System.out.println(dateTime.withDate(2012, 12, 20));
    System.out.println(isAfterPayDay(dateTime));
    System.out.println(getFullTime(dateTime));
    System.out.println(daysToNewYear(dateTime.toLocalDate()));
    System.out.println(isRentalOverdue(dateTime));
    System.out.println(getBirthMonthText(dateTime.toLocalDate()));
    System.out.println(isLeap(dateTime));

    System.out
        .println("====================construct a datetime use special time===========================");
    DateTime dateTime5 = new DateTime(2000, // year
        1, // month
        1, // day
        0, // hour (midnight is zero)
        0, // minute
        0, // second
        0 // milliseconds
    );
    System.out.println(dateTime5);

    System.out
        .println("====================Time Zone===========================");

    DateTimeZone zone = DateTimeZone.forID("Asia/Tokyo");
    Chronology gregorianJuian = GJChronology.getInstance(zone);
    System.out.println(gregorianJuian);

    System.out
        .println("====================use joda to cal and then to JDK===========================");
    Calendar calendar = Calendar.getInstance();
    DateTime dateTime4 = new DateTime(2000, 1, 1, 0, 0, 0, 0);
    System.out.println(dateTime4.plusDays(45).plusMonths(1).dayOfWeek()
        .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    calendar.setTime(dateTime4.toDate());

    System.out
        .println("====================Duration Period Interval===========================");
    DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
    DateTime end = new DateTime(2004, 12, 26, 0, 0, 0, 0);

    // duration in ms between two instants
    Duration dur = new Duration(start, end);
    System.out.println(dur);

    // calc will be the same as end
    DateTime calc = start.plus(dur);
    System.out.println(calc);

    Duration duration = new Duration(dateTime.getMillis());
    System.out.println(duration);

    Period period = new Period(dateTime.getMillis());
    System.out.println(period);

    Interval interval = new Interval(dateTime4.getMillis(),
        System.currentTimeMillis());
    System.out.println(interval);

    System.out
        .println("====================construct anbd calculate===========================");
    // we hope print this format of date:the day of 45 days later from Y2K,and
    // the same day of next month
    // and the weekend of that day.
    DateTime dateTime3 = new DateTime(2000, 1, 1, 0, 0, 0, 0);
    System.out.println(dateTime3.plusDays(45).plusMonths(1).dayOfWeek()
        .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));

    System.out
        .println("====================calculate the last day of last month===========================");
    LocalDate withMaximumValue = LocalDate.now().minusMonths(1).dayOfMonth()
        .withMaximumValue();
    System.out.println(withMaximumValue);

    System.out
        .println("====================calculate the first Tuesday after the fist Monday of November in any year===========================");
    LocalDate plusDays = LocalDate.now().monthOfYear().setCopy(11) // November
        // .setCopy("November") //November
        .dayOfMonth() // Access day of month property
        .withMinimumValue() // Get its minimum value
        .plusDays(6) // Add 6 days
        .dayOfWeek() // Access Day of week property
        .setCopy(1) // Set to Monday(it will round down auto)
        // .setCopy("Monday")
        .plusDays(1); // Give us Tuesday
    System.out.println(plusDays);

    System.out
        .println("====================two weeks later from now===========================");
    LocalDate plusWeeks = LocalDate.now().plusWeeks(2);
    System.out.println(plusWeeks);

    System.out
        .println("====================90 days later from tomorrow===========================");
    LocalDate plusDays2 = LocalDate.now().plusDays(1).plusDays(90);
    System.out.println(plusDays2);

    System.out
        .println("====================the last day at the second month after five years' later===========================");
    LocalDate withMaximumValue2 = LocalDate.now().plusYears(5).monthOfYear()
        .setCopy(2).dayOfMonth().withMaximumValue();
    System.out.println(withMaximumValue2);

    System.out
        .println("====================from ReadableInstant to JDK date===========================");
    Calendar calendar2 = dateTime.toCalendar(Locale.getDefault());
    Date date = dateTime.toDate();

    System.out
        .println("====================With ISO=8601===========================");
    String string = dateTime.toString(ISODateTimeFormat.basicDateTime());
    String string2 = dateTime.toString(ISODateTimeFormat
        .basicDateTimeNoMillis());
    String string3 = dateTime
        .toString(ISODateTimeFormat.basicOrdinalDateTime());
    String string4 = dateTime.toString(ISODateTimeFormat.basicWeekDateTime());
    System.out.println(string);
    System.out.println(string2);
    System.out.println(string3);
    System.out.println(string4);

    System.out
        .println("====================With SimpleDateFormat===========================");
    LocalDate localDate = new LocalDate(2014, 9, 23);
    System.out.println(localDate.toString("E", Locale.CHINA));

    LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM
    System.out.println(localTime);
    System.out.println(dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
    System.out.println(dateTime.toString("MM-dd-yyyy hh:mm:ss"));
    System.out.println(dateTime.toString("EEEE dd MMMM,yyyy HH:mm:ss.ssa"));
    System.out.println(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
    System.out.println(dateTime.toString("MM/dd/yyyy HH:mm Z"));

    System.out.println("====================Days===========================");
    System.out.println(Days.FIVE);
    System.out.println(Days.FOUR);
    System.out.println(Days.daysBetween(dateTime3, dateTime));
  }
}
