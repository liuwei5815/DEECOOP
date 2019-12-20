//package com.xy.vmes.deecoop.timer.finance;
//
//import com.baomidou.mybatisplus.plugins.pagination.Pagination;
//import com.xy.vmes.common.util.DateFormat;
//import com.xy.vmes.common.util.StringUtil;
//import com.xy.vmes.entity.FinancePeriod;
//import com.xy.vmes.entity.PurchaseCompanyPeriod;
//import com.xy.vmes.entity.PurchasePaymentBuild;
//import com.xy.vmes.entity.PurchasePaymentHistory;
//import com.xy.vmes.serviceImp.*;
//import com.yvan.HttpUtils;
//import com.yvan.PageData;
//import com.yvan.common.util.Common;
//import com.yvan.springmvc.ResultModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.text.MessageFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 秒 分 时 月中的某天 月 每周的某天
// * Seconds Minutes Hours DayofMonth Month DayofWeek
// *
// * 每天12点30分执行        (0 30 12 * * ?)
// * 每月1号凌晨1点执行       (0 0 1 1 * ?)
// *
// */
//@Component
//@EnableScheduling
//public class FinanceSaleTask {
//    private Logger logger = LoggerFactory.getLogger(FinanceSaleTask.class);
//
//    //PurchasePaymentTask.initTimer(yyyy-MM-dd HH:mm:ss):开始执行
//    private String begin_logger_msg_temp = "{0}.{1}({2}):{3}";
//    //PurchasePaymentTask.initTimer(yyyy-MM-dd HH:mm:ss):结束执行:总耗时()毫秒
//    private String end_logger_msg_temp = "{0}.{1}({2}):{3}:总耗时({4})毫秒";
//
//
//
//    @Autowired
//    private FinancePeriodService financePeriodService;
//
//    @Autowired
//    private FinanceBillService financeBillService;
//
//
//    /**
//     * 每月1号凌晨1点执行：(0 0 1 1 * ?)
//     *
//     */
//    @Scheduled(cron = "0 0 1 1 * ?")
//    public void initTimer() throws Exception {
//        Long startTime = System.currentTimeMillis();
//        String dateTimeStr = DateFormat.date2String(new Date(), DateFormat.DEFAULT_DATETIME_FORMAT);
//        String begin_logger_msg = MessageFormat.format(begin_logger_msg_temp,
//                "FinanceSaleTask",
//                "initTimer",
//                dateTimeStr,
//                "开始执行");
//        logger.info(begin_logger_msg);
//
//        //1. 查询系统所有企业-是否采购企业付款期设定-未设定系统设定默认值
//        List<Map> mapList = null;
//        try {
//            PageData findMap = new PageData();
//            mapList = financePeriodService.getDataListPage(findMap,null);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        if (mapList == null || mapList.size() == 0) {
//            String end_logger_msg = this.endLoggerMsg(startTime);
//            logger.info(end_logger_msg);
//            return;
//        }
//
//        for(int i=0;i<mapList.size();i++){
//            Map financePeriodMap = mapList.get(i);
//            if(financePeriodMap.get("companyId")!=null){
//                String companyId = (String)financePeriodMap.get("companyId");
//
//                GregorianCalendar gc =new GregorianCalendar();
//                SimpleDateFormat sdf  =new SimpleDateFormat("yyyyMM");
//                PageData pageData = new PageData();
//                pageData.put("company_id",companyId);
//                List<FinancePeriod> financePeriodList = financePeriodService.selectByColumnMap(pageData);
//                if(financePeriodList!=null&&financePeriodList.size()>0){
//                    for(int j=0;j<financePeriodList.size();j++){
//                        FinancePeriod financePeriod = financePeriodList.get(j);
//                        if(j==0){
//                            pageData.put("genre",Common.DICTIONARY_MAP.get("customerGenre"));
//                            List<Map> varList = financeBillService.getFinanceReceiveView(pageData,null);
//                            if(varList!=null&&varList.size()>0){
//                                for(int k=0;k<varList.size();k++){
//                                    Map map = varList.get(k);
//                                    financeBillService.saveFinanceHistory(map);
//                                }
//                            }
//                            Date preDate = financePeriod.getCurrentPeriodDate();
//                            gc.setTime(preDate);
//                            gc.add(2,+1);
//                            gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
//                            Date curDate = gc.getTime();
//                            String curPeriod = sdf.format(curDate);
//                            financePeriod.setCurrentPeriodDate(curDate);
//                            financePeriod.setCurrentPeriod(curPeriod);
//                            financePeriodService.update(financePeriod);
//
//                        }else if(j>=1){
//                            financePeriodService.deleteById(financePeriod.getId());
//                        }
//                    }
//                }else{
//                    financeBillService.saveFinancePeriod(companyId);
//                }
//            }
//
//        }
//
//        String end_logger_msg = this.endLoggerMsg(startTime);
//        logger.info(end_logger_msg);
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////
//    private String endLoggerMsg(Long startTime) {
//        Long endTime = System.currentTimeMillis();
//        String dateTimeStr = DateFormat.date2String(new Date(), DateFormat.DEFAULT_DATETIME_FORMAT);
//        return MessageFormat.format(end_logger_msg_temp,
//                "FinanceSaleTask",
//                "initTimer",
//                dateTimeStr,
//                "结束执行",
//                (endTime-startTime));
//    }
//
//}
