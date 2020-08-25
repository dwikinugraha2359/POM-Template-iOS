package com.media2359.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;

/**
 * Utils
 */
public class HelperFunc {
    public HelperFunc() {
    }

    // to clean download dir
    public void cleanDownloadDir() throws IOException {
        // clean all file on download directory
        FileUtils.cleanDirectory(new File("/Users/2359id/developments/office/GoldBell-Web-Automation/download/"));
        System.out.println("Successfully clean on download dir");
    }

    // to clean pic result of test
    public static void deleteDirectoryFile() throws IOException {
        // to delete directory
        FileUtils.deleteDirectory(new File("test-output/pic/" + generateDateNHour()));
        System.out.println("Successfully delete pic dir");
    }

    // get number on string
    public static String stripNonDigits(final CharSequence input) {
        final StringBuilder sb = new StringBuilder(input.length() );
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // generate random number in range
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static Boolean checkDateBetween(Date start, Date end, Date act) {
        return start.compareTo(act) * act.compareTo(end) > 0;
    }

    // to generate date with hour
    public static String generateDateNHour() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // Get list of week
    public static List<Date> getListofWeek(int weekOfYear) {
        List<Date> result = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.add(cal.getTime());
        for (int i = 1; i < 7; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            result.add(cal.getTime());
        }
        return result;
    }

    // Get date list of week
    public static Date getDateofWeek(int weekOfYear, int expDay) {
        List<Integer> result = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        // cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // System.out.println(sdf.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_WEEK, expDay);
        // result.add(Integer.parseInt(formatter.format(cal.getTime())));
        return cal.getTime();
    }

    // Get list of week - format yyyy-MM-dd (2019-01-13)
    public static List<String> getListofWeekyyyMMdd(int weekOfYear) {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.add(formatter.format(cal.getTime()));
        for (int i = 1; i <= 7; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            result.add(formatter.format(cal.getTime()));
        }
        return result;
    }

    // get date
    public static String getDateString(int weekOfYear, int dayExp) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        cal.set(Calendar.DAY_OF_WEEK, dayExp);
        Reporter.log(formatter.format(cal.getTime()));
        return formatter.format(cal.getTime());
    }

    // conver date into month year
    public static String dateToMonthYear(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
        return formatter.format(date);
    }

    // convert date into E, MMM dd (Thu, Jun 20)
    public static String dateToEMMMdd(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd");
        return formatter.format(date);
    }

    // convert date into string
    public static String dateToString(Date date, String dateFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String strDate = formatter.format(date);
        return strDate;
    }

    // convert string into date
    public static Date stringToDate(String stringDate) throws ParseException {
        Date resultDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return resultDate;
    }

    public static Date stringToDate(String stringDate, String dateFormat) throws ParseException {
        Date resultDate = new SimpleDateFormat(dateFormat).parse(stringDate);
        return resultDate;
    }

    public static Integer dateToWeekOfYear(Date date1) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date1);
        return cl.get(Calendar.WEEK_OF_YEAR);
    }

    // convert (E, MMM dd yyyy) string into date ----- galaxy note 8
    // convert (E dd MMM yyyy) string into date ----- galaxy S9
    // convert string into date
    public static Date stringDPToDate(String stringDate, String dateFormat) throws ParseException {
        Date resultDate = new SimpleDateFormat(dateFormat).parse(stringDate);
        return resultDate;
    }

    public static List<String> stringBoundToListXY(String stringBound, Boolean isX) {
        if (true) {
            return Arrays.asList(StringUtils.substringsBetween(stringBound, "[", ","));
        } else {
            return Arrays.asList(StringUtils.substringsBetween(stringBound, ",", "]"));
        }
    }

}