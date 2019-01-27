package com.ichack.educate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

  public static String unixToDate(long unix) {
    final int TO_MILLISECONDS = 1000;
    Date unixDate = new Date(unix * TO_MILLISECONDS);
    SimpleDateFormat dateFormatOut = new SimpleDateFormat("EE dd MMM yyyy", Locale.UK);
    return dateFormatOut.format(unixDate);
  }

  public static String unixToTime(long unix) {
    final int TO_MILLISECONDS = 1000;
    Date unixDate = new Date(unix * TO_MILLISECONDS);
    SimpleDateFormat timeFormatOut = new SimpleDateFormat("HH:mm", Locale.UK);
    return timeFormatOut.format(unixDate);
  }
}
