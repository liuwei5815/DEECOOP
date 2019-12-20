package com.yvan;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DateUtils 日期工具类
 *
 * @author luoyifan 2014/10/2 4:02:52
 */
public class DateUtils {

    private static final TimeZone TZ = TimeZone.getTimeZone("GMT+:08:00");
    private static final SimpleDateFormat FORMAT_DATE_TIME;
    private static final SimpleDateFormat FORMAT_DATE_TIME1;
    private static final SimpleDateFormat FORMAT_TIME;
    private static final SimpleDateFormat FORMAT_DATE;
    private static final SimpleDateFormat FORMAT_MONTH_DATE;

    private static final SimpleDateFormat FORMAT_YYYYMMDD;
    private static final SimpleDateFormat FORMAT_DATE_TIME_CHINESE;

    static {
        FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FORMAT_DATE_TIME.setTimeZone(TZ);

        FORMAT_DATE_TIME1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        FORMAT_DATE_TIME1.setTimeZone(TZ);
        FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
        FORMAT_TIME.setTimeZone(TZ);

        FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
        //FORMAT_DATE.setTimeZone(TZ);

        FORMAT_MONTH_DATE = new SimpleDateFormat("yyyyMM");
        FORMAT_MONTH_DATE.setTimeZone(TZ);

        FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        FORMAT_YYYYMMDD.setTimeZone(TZ);

        FORMAT_DATE_TIME_CHINESE = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        //FORMAT_DATE_TIME_CHINESE.setTimeZone(TZ);
    }
    //比较时间大小
    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * 2016-02-23 08:09:01
     */
    public static Timestamp fromPatter(String value, String patter, Timestamp defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        try {
            return new Timestamp(sdf.parse(value).getTime());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance(TZ);
    }

    public static String toDateTimeStr(Date date) {
        return FORMAT_DATE_TIME.format(date);
    }

    public static String toTimeStr(Date date) {
        return FORMAT_TIME.format(date);
    }

    public static String toDateStr(Date date) {
        return FORMAT_DATE.format(date);
    }

    public static String toMonthStr(Date date) {
        return FORMAT_MONTH_DATE.format(date);
    }

    public static Integer toyyyyMMdd(Date date) {
        return Integer.parseInt(FORMAT_YYYYMMDD.format(date));
    }

    /**
     * 2016-02-23
     */
    public static Date fromDate(String v) {
        try {
            return FORMAT_DATE.parse(v);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 20160223
     */
    public static Date fromDate(Integer vs) {
        try {
            String v = Integer.toString(vs);
            Calendar calc = getCalendar();
            calc.set(Calendar.YEAR, Integer.parseInt(v.substring(0, 4)));
            calc.set(Calendar.MONTH, Integer.parseInt(v.substring(4, 6)) - 1);
            calc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(v.substring(6, 8)));

            calc.set(Calendar.HOUR_OF_DAY, 0);
            calc.set(Calendar.MINUTE, 0);
            calc.set(Calendar.SECOND, 0);
            return calc.getTime();

        } catch (Exception e) {
            return new Date();
        }
    }

    public static int getYear(Timestamp now) {
        Calendar calc = getCalendar();
        calc.setTime(new Date(now.getTime()));
        return calc.get(Calendar.YEAR);
    }

    public static int getMonth(Timestamp now) {
        Calendar calc = getCalendar();
        calc.setTime(new Date(now.getTime()));
        return calc.get(Calendar.MONTH);
    }

    public static int getDay(Timestamp now) {
        Calendar calc = getCalendar();
        calc.setTime(new Date(now.getTime()));
        return calc.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 按最短字符串长度表示年月日
     * 1位年+1位月+1位日
     * 年前年份-startYear 采用36进制(0-9/A-Z)
     * 月份 采用36进制(0-9/A-Z)
     * 日 采用36进制(0-9/A-Z)
     */
    public static String getShortYMD(Timestamp time, int startYear) {
        return "" + YvanUtil.getShortDig(getYear(time) - startYear) +
            YvanUtil.getShortDig(getMonth(time)) +
            YvanUtil.getShortDig(getDay(time));
    }


    /**
     * 2016-02-23 08:09:01
     */
    public static Date fromDateTime(String v) {
        try {
            return FORMAT_DATE_TIME.parse(v);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 2016-02-23 08:09
     */
    public static Date fromDateTime1(String v) {
        try {
            return FORMAT_DATE_TIME1.parse(v);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * <pre>
     * 2016-02-23 return 2016-02-24
     * 2016-02-29 return 2016-03-01
     * </pre>
     */
    public static Date addDay(Date v, int days) {
        final Calendar calc = getCalendar();
        calc.setTime(v);
        calc.add(Calendar.DAY_OF_WEEK, days);
        return calc.getTime();
    }

    /**
     * 获取按天累加的日期序列
     */
    public static List<Date> getDaySeq(Date dt1, Date dt2) {
        final Calendar calc = getCalendar();

        List<Date> r = new ArrayList<>();
        for (calc.setTime(dt1); calc.getTime().compareTo(dt2) <= 0;
            calc.add(Calendar.DAY_OF_WEEK, 1)) {
            r.add(calc.getTime());
        }
        return r;
    }

    /**
     * <pre>
     * 获取按月累加的日期序列, 例如 201601 201702
     * 返回  201601 201602 201603
     * </pre>
     */
    public static List<String> getMonthSeq(int d1, int d2) {
        Date dt1 = fromDate(Integer.parseInt(Integer.toString(d1) + "01"));
        Date dt2 = fromDate(Integer.parseInt(Integer.toString(d2) + "01"));
        return getMonthSeq(dt1, dt2);
    }

    /**
     * 获取按月累加的日期序列, 返回 201601 201602 201603
     */
    public static List<String> getMonthSeq(Date dt1, Date dt2) {
        final Calendar calc = Calendar.getInstance();

        calc.setTime(dt1);
        if (calc.get(Calendar.DAY_OF_MONTH) > 1) {
            dt1 = fromDate(Integer.parseInt(toMonthStr(dt1).substring(0, 6) + "01"));
        }
        calc.setTime(dt2);
        if (calc.get(Calendar.DAY_OF_MONTH) > 1) {
            dt2 = fromDate(Integer.parseInt(toMonthStr(dt2).substring(0, 6) + "01"));
        }

        List<String> r = new ArrayList<>();
        for (calc.setTime(dt1); calc.getTime().compareTo(dt2) <= 0; calc.add(Calendar.MONTH, 1)) {
            r.add(toMonthStr(calc.getTime()));
        }
        return r;
    }

    public static void main(String[] args) {
        String d1 = "2016-02-23";
        String d2 = "2016-03-02";
        System.out.println(d1);

        System.out.println(toDateTimeStr(fromDate(20161018)));

        // System.out.println(fromDate(d1).compareTo(fromDate(d2)));
        // System.out.println(fromDate(d2).compareTo(fromDate(d1)));
        // System.out.println(fromDate(d1).compareTo(fromDate(d1)));

        List<Date> seq = getDaySeq(fromDate(d1), fromDate(d2));
        for (Date d : seq) {
            System.out.println(toDateStr(d));
        }
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static int seconds(Timestamp t1, Timestamp t2) {
        return Conv.NI((t1.getTime() - t2.getTime()) / 1000);
    }

    public static Timestamp addSeconds(Timestamp t1, int seconds) {
        return new Timestamp(t1.getTime() + seconds * 1000);
    }


    public static String toChineseFmt(Timestamp startTime) {
        try {
            return FORMAT_DATE_TIME_CHINESE.format(new Date(startTime.getTime()));
        } catch (Exception e) {
            return "";
        }
    }



}
