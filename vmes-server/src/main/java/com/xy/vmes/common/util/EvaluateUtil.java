package com.xy.vmes.common.util;

import com.yvan.common.util.Common;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by 46368 on 2018/11/14.
 */
public class EvaluateUtil {

    /**
     * 公式计算方法
     * @param parmMap  公式中的参数map
     * @param formula  公式字符串
     * @return
     */
    public static BigDecimal formulaReckon(Map<String, Object> parmMap, String formula) {
        if (parmMap == null || parmMap.size() == 0) {return BigDecimal.valueOf(0D);}
        if (formula == null || formula.trim().length() == 0) {return BigDecimal.valueOf(0D);}

        try {
            Binding binding = new Binding();
            for (Iterator iterator = parmMap.keySet().iterator(); iterator.hasNext();) {
                String mapkey = (String)iterator.next();
                String value = parmMap.get(mapkey).toString();
                binding.setVariable(mapkey, new BigDecimal(value));
            }

            GroovyShell shell = new GroovyShell(binding);
            Object valueObject = shell.evaluate(formula);
            if (valueObject != null) {
                return new BigDecimal(valueObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BigDecimal.valueOf(0D);
    }

//    /**
//     * 公式计算方法
//     * isScale: 是否需要四舍五入(Y:需要四舍五入(默认值) N:无需四舍五入) is null 需要四舍五入
//     * decimalCount: 保留小数位数 (最小:0位 最大:4位) 默认值(小数2位)
//     *
//     * @param parmMap       公式中的参数map
//     * @param formula       公式字符串
//     * @param isScale       (允许为空)是否需要四舍五入(Y:需要四舍五入 N:无需四舍五入)
//     * @param decimalCount  (允许为空)保留小数位数
//     * @return
//     */
//    public static BigDecimal formulaReckon(Map<String, Object> parmMap,
//                                           String formula,
//                                           String isScale,
//                                           Integer decimalCount) {
//        if (parmMap == null || parmMap.size() == 0) {return null;}
//        if (formula == null || formula.trim().length() == 0) {return null;}
//
//        // 当(isScale is null 或 isScale is not in (Y,N) )设定 系统设定为需要四舍五入
//        if (isScale == null || isScale.trim().length() == 0) {
//            isScale = Common.SYS_ISSCALE_TRUE;
//        } else if ("Y,N".indexOf(isScale) == -1) {
//            isScale = Common.SYS_ISSCALE_TRUE;
//        }
//
//        //系统设定默认保留小数位数 默认保留小数2位
//        if (decimalCount == null) {
//            decimalCount = Integer.valueOf(Common.SYS_NUMBER_FORMAT_2);
//        }
//
//        Binding binding = new Binding();
//        for (Iterator iterator = parmMap.keySet().iterator(); iterator.hasNext();) {
//            String mapkey = (String)iterator.next();
//            String value = parmMap.get(mapkey).toString();
//            binding.setVariable(mapkey, new BigDecimal(value));
//        }
//
//        GroovyShell shell = new GroovyShell(binding);
//        Object valueObject = shell.evaluate(formula);
//
//        if (valueObject != null) {
//            BigDecimal valueBig = new BigDecimal(valueObject.toString());
//
//            //四舍五入到2位小数
//            //return valueBig.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
//
//            //系统是否需要四舍五入 Y:需要四舍五入 指定小数位
//            if (Common.SYS_ISSCALE_TRUE.equals(isScale)) {
//                valueBig = valueBig.setScale(decimalCount.intValue(), BigDecimal.ROUND_HALF_UP);
//
//                //系统是否需要四舍五入 N:无需四舍五入 (无四舍五入)指定小数位
//            } else if (Common.SYS_ISSCALE_FALSE.equals(isScale)) {
//                valueBig = valueBig.setScale(decimalCount.intValue(), BigDecimal.ROUND_DOWN);
//            }
//
//            return valueBig;
//        }
//
//        return null;
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 计量单位数量-公式转换-计价单位数量
     * @param count    数量(计量单位)
     * @param formula  单位换算公式  P=8*N  N(计量单位数量) P(计价单位数量)
     * @return
     */
    public static BigDecimal countFormulaN2P(BigDecimal count, String formula) {
        if (count == null) {return BigDecimal.valueOf(0D);}
        if (formula == null || formula.trim().length() == 0) {return BigDecimal.valueOf(0D);}

        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("N", count);

        return EvaluateUtil.formulaReckon(parmMap, formula);
    }

    /**
     * 计价单位数量-公式转换-计量单位数量
     * @param count    数量(计价单位)
     * @param formula  单位换算公式  N=8*P  N(计量单位数量) P(计价单位数量)
     * @return
     */
    public static BigDecimal countFormulaP2N(BigDecimal count, String formula) {
        if (count == null) {return BigDecimal.valueOf(0D);}
        if (formula == null || formula.trim().length() == 0) {return count;}

        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("P", count);

        return EvaluateUtil.formulaReckon(parmMap, formula);
    }

    public static void main(String args[]) throws ParseException {

//        Binding binding = new Binding();
//
//        binding.setVariable("F",2.5);
//        binding.setVariable("T",30);
//        binding.setVariable("A",100);
//        binding.setVariable("P0",100);
//
//        //binding.setVariable("language", "Groovy");
//
//        GroovyShell shell = new GroovyShell(binding);
//
//        Object F1 =shell.evaluate("P1=(1+0.1 * (F/100) * T)*P0");
//
//        //Object F2 =shell.evaluate("P1=P0*(0.055*0.20+1.0011)+A; return P1 ");
//
//        System.out.println(F1.toString());
//        //System.out.println(F2);

//        Map<String, Object> parmMap = new HashMap<String, Object>();
//        //parmMap.put("P", "10");
//
//        parmMap.put("F", "2.5");
//        parmMap.put("T", Integer.valueOf(30));
//        parmMap.put("A", "100");
//        parmMap.put("P0", "100");
//
//        //BigDecimal valueBig = EvaluateUtil.formulaReckon(parmMap, "N=8*P");
//        BigDecimal valueBig = EvaluateUtil.formulaReckon(parmMap, "P1=(1+0.1 * (F/100) * T)*P0");
//
//        System.out.println("valueBig: " + valueBig.toString());

        //2019-07-30 第五个星期二
        //2019-08-01 第一个星期四

//        String beginPlanStr = "2019-07-05";
//        Date beginPlan = DateFormat.dateString2Date(beginPlanStr, DateFormat.DEFAULT_DATE_FORMAT);
//        //System.out.println("timeLong: " + beginPlan.getTime());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(beginPlan);

//        int temp = calendar.get(Calendar.DAY_OF_WEEK);
//        String tempStr = Common.SYS_DAYOFWEEK_TO_WEEKNAME.get(Integer.valueOf(temp));
//        System.out.println("tempStr: " + tempStr);

//        //当前月第几周
//        int weekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
//        //System.out.println("当前月第 " + weekInMonth + " 周");
//        //当前日期星期几
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//        String tempStr = "每月{0}个{1}";
//        String sysPeriodTypeName = MessageFormat.format(tempStr,
//                Common.SYS_WEEK_WEEKINMONTH.get(Integer.valueOf(weekInMonth)),
//                Common.SYS_DAYOFWEEK_TO_WEEKNAME.get(Integer.valueOf(dayOfWeek)));
//        System.out.println("sysPeriodTypeName: " + sysPeriodTypeName);

//        String endPlanStr = "2019-07-07";
//        Date endPlan = DateFormat.dateString2Date(endPlanStr, DateFormat.DEFAULT_DATE_FORMAT);
//        int count = DateFormat.getDays(beginPlan, endPlan);
//        //int count = CalendarUtil.getDaysNoAfter(beginPlan, endPlan);
//        System.out.println("count: " + count);


//        String dayOfYearStr = DateFormat.date2String(beginPlan, "MM月dd日");
//        System.out.println("每年" + dayOfYearStr);


        //2019-07-30 第五个星期二
        //2019-08-01 第一个星期四

        //当前月第几周
        //int weekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        //System.out.println("当前月第 " + weekInMonth + " 周");
        //当前日期星期几
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//        int day1 = DateFormat.findDayByWeekMin(Calendar.TUESDAY);
//        int day2 = DateFormat.findDayByWeekMax(dayOfWeek);
//
//        int addDay = (4-1) * 7 + (day1 + day2 + 1);
//        String newDateStr = DateFormat.getAddDay(beginPlanStr, DateFormat.DEFAULT_DATE, addDay, DateFormat.DEFAULT_DATE_FORMAT);
//        System.out.println("newDateStr:" + newDateStr);

        //int Month = calendar.get(Calendar.MONTH);
        //System.out.println("Month:" + Month);

//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH );
//        System.out.println("year:" + year + " month:" + month + " day:" + day);

//        Date dayOfMonthDate = DateFormat.findLastDayByMonth(year, month, DateFormat.DEFAULT_DATE_FORMAT);
//        String dayStr = DateFormat.date2String(dayOfMonthDate, DateFormat.DEFAULT_DATE_FORMAT);
//        System.out.println("dayStr:" + dayStr);

//        calendar.add(Calendar.DATE, 7);
//        Date newDate = calendar.getTime();
//        String newDateStr = DateFormat.date2String(newDate, DateFormat.DEFAULT_DATE_FORMAT);
//        System.out.println("newDateStr:" + newDateStr);

//        BigDecimal ratio = BigDecimal.valueOf(3.564D); //[1,9]
//        System.out.println("ratio:" + ratio.toString());
//
////        //四舍五入 2位小数 (3.57)
////        ratio = ratio.setScale(2, BigDecimal.ROUND_HALF_UP);
////        System.out.println("ratio:" + ratio.toString());
//
//        //保留小数(无四舍五入) 2位
//        ratio = ratio.setScale(2, BigDecimal.ROUND_DOWN);
//        System.out.println("ratio:" + ratio.toString());

//        String formula = "P=8*N";
//        BigDecimal count = BigDecimal.valueOf(3.45);
//
//        BigDecimal bigValue = EvaluateUtil.countFormulaN2P(count, formula);
//        System.out.println("P=8*N:= " + bigValue.toString());

//        String isScale = Common.SYS_ISSCALE_TRUE;
//        //String isScale = Common.SYS_ISSCALE_FALSE;
//        Integer decimalCount = Integer.valueOf(4);
//
//        BigDecimal bigValue = BigDecimal.valueOf(3.5675678D);
//        System.out.println("bigValue:" + bigValue.toString());
//
//        bigValue = StringUtil.scaleDecimal (bigValue, isScale, decimalCount);
//        System.out.println("ratio:" + bigValue.toString());

        String str = "2019";
        String strTemp = str.substring(2, str.trim().length());
        System.out.println("strTemp:" + strTemp.toString());
    }
}
