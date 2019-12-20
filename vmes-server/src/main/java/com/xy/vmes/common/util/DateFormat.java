package com.xy.vmes.common.util;

import com.yvan.common.util.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFormat {
    /**缺省日期格式*/
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**缺省长日期格式,精确到秒*/
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_YEAR = "YEAR";
    public static final String DEFAULT_MONTH = "MONTH";
    public static final String DEFAULT_DATE = "DAY";

    public static Date dateString2Date(String dateStr, String formatStr) {
        if (dateStr == null || dateStr.trim().length() == 0) {return null;}
        if (formatStr == null || formatStr.trim().length() == 0) {return null;}

        java.util.Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            date = format.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String date2String(Date date, String formatStr) {
        if (date == null) {return null;}
        if (formatStr == null || formatStr.trim().length() == 0) {return null;}

        try{
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.format(date);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * @param date String 要改变的日期
     * @param field int 日期改变的字段，YEAR,MONTH,DAY
     * @param amount int 改变量
     * @param strFormat 日期返回格式
     * @return
     * @throws ParseException
     * @author caohongbin
     */
    public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
        //当前日期和前一天
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(strFormat);

        Calendar rightNow = Calendar.getInstance();
        Date tempdate = dateFormat.parse(date);
        rightNow.setTime(tempdate);

        int intField = 0;
        String tmpField = field.toUpperCase();

        intField = Calendar.DATE;
        if (tmpField.equals(DEFAULT_YEAR)) {
            intField = Calendar.YEAR;
        } else if (tmpField.equals(DEFAULT_MONTH)) {
            intField = Calendar.MONTH;
        } else if (tmpField.equals(DEFAULT_DATE)) {
            intField = Calendar.DATE;
        }

        rightNow.add(intField, amount);
        String day = dateFormat.format(rightNow.getTime());
        return day;
    }

    //获得两个时间之间的天数,考虑隔年的情况
    public static int getDays(Date fDate, Date oDate) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setTime(fDate);
        d2.setTime(oDate);

        if (d1.after(d2)) {
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            } while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    public static int findDayByWeekMin(int dayOfWeek) {
        int toWeekMin = 0;
        List<Integer> dayList = Common.SYS_DAYOFWEEK_LIST;
        if (dayList == null || dayList.size() == 0) {return toWeekMin;}

        for (Integer intObject : dayList) {
            if (intObject != null && intObject.intValue() == dayOfWeek) {
                toWeekMin = dayList.indexOf(intObject);
            }
        }

        return toWeekMin;
    }

    public static int findDayByWeekMax(int dayOfWeek) {
        int toWeekMax = 0;
        List<Integer> dayList = Common.SYS_DAYOFWEEK_LIST;
        if (dayList == null || dayList.size() == 0) {return toWeekMax;}

        int index = DateFormat.findDayByWeekMin(dayOfWeek);
        int indexMax = dayList.size() - 1;

        toWeekMax = indexMax - index;
        return toWeekMax;
    }

    /**
     * 计算给定月最后一天
     *
     * @param year       年份
     * @param month      自然月份 取值范围[1,12] (注意jdk JANUARY:一月:0)
     * @param strFormat  日期格式
     * @return
     */
    public static Date findLastDayByMonth(int year, int month, String strFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE,-1);

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            date = sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String args[]) throws ParseException {
        //Date date = DateFormat.dateString2Date("201902", "yyyyMM");

        String dateStr = DateFormat.getAddDay("201901", DateFormat.DEFAULT_MONTH, -1, "yyyyMM");

        //String dateStr = date2String(date, "yyyy-MM-dd");
        System.out.println("dateStr: " + dateStr);
    }
}
