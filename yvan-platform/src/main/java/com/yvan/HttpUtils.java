package com.yvan;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.platform.HttpParameterParser;
import com.yvan.platform.RestException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.math.BigDecimal;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luoyifan on 2018/7/14.
 */
public class HttpUtils {

    public static HttpServletRequest currentRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static HttpServletResponse currentResponse() {
        try {
            //设置跨域请求参数
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Max-Age", "100");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Allow-Credentials", "false");
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从当前请求中获取分页方法和内容
     */
    public static Pagination parsePagination(PageData pd) {
//        HttpServletRequest request = currentRequest();
//        HttpParameterParser parser = HttpParameterParser.newInstance(request);
//        PageData  parser = parsePageData();
//        PageData parser = new PageData(request);
        int pageNo = StringUtils.isEmpty(pd.getString("pageNo"))?1:Integer.parseInt(pd.getString("pageNo"));
        int pageSize = StringUtils.isEmpty(pd.getString("pageSize"))?20:Integer.parseInt(pd.getString("pageSize"));
//        String orderBy = parser.getString("orderBy");
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0 || pageSize > 500) {
            pageSize = 20;
        }

        Pagination pagination = new Page(pageNo, pageSize);
        pagination.setOpenSort(false);
        pd.put("pg",pagination);
        return pagination;
    }

    /**
     * 从当前请求中获取分页方法和内容
     */
    public static PageData parsePageData() {
        HttpServletRequest request = currentRequest();
        PageData pd = new PageData(request);
        return pd;
    }

    public static Object pageData2Entity(Map mapObj, Object entityObj) {
        if (mapObj == null) {
            throw new RestException("", "参数错误: PageData 对象为空！");
        }
        if (entityObj == null) {
            throw new RestException("", "参数错误: entityObj 业务对象为空！");
        }

        //1. 遍历<Map>对象
        for (Iterator iterator = mapObj.keySet().iterator(); iterator.hasNext(); ) {
            String mapKey = (String) iterator.next();
            if(mapObj.get(mapKey)==null){continue;}
            String mapValue = mapObj.get(mapKey).toString().trim();
            if ("cdate,udate".indexOf(mapKey) != -1) {continue;}
            if (mapValue.length() == 0) {continue;}

            //2. 遍历业务对象属性
            Class clazz = entityObj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String paraKey = field.getName().trim();
                String attribute = field.toString();

                if (mapKey.equals(paraKey)) {
                    try {
                        if (attribute.indexOf("java.lang.String") != -1) {
                            field.set(entityObj, mapValue);
                        } else if (attribute.indexOf("java.lang.Integer") != -1) {
                            Integer valueObj = Integer.valueOf(mapValue);
                            field.set(entityObj, valueObj);
                        } else if (attribute.indexOf("java.util.Date") != -1) {
                            Date valueObj = new Date();
                            if (mapValue != null && mapValue.trim().length() == "yyyy-MM-dd".length()) {
                                valueObj = DateUtils.fromDate(mapValue);
                            } else if (mapValue != null && mapValue.trim().length() == "yyyy-MM-dd HH:mm:ss".length()) {
                                valueObj = DateUtils.fromDateTime(mapValue);
                            }
                            field.set(entityObj, valueObj);
                        } else if (attribute.indexOf("java.math.BigDecimal") != -1) {
                            BigDecimal valueObj = new BigDecimal(mapValue);
                            field.set(entityObj, valueObj);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RestException("", "数据类型转换异常，请检查输入数据是否规范！");
                    }
                }
            }
        }

        return entityObj;
    }

    public static PageData entity2PageData(Object entityObj, PageData pageData){
        if (pageData == null) {pageData = new PageData();}
        if (entityObj == null) {return pageData;}

        Class clazz = entityObj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String paraKey = field.getName().trim();
            String strTemp = "serialVersionUID";
            if (strTemp.indexOf(paraKey) != -1) {continue;}

            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(paraKey, clazz);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(entityObj);
                if (value != null) {
                    pageData.put(paraKey, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pageData.put("mapSize", Integer.valueOf(pageData.size()));
        return pageData;
    }

    public static String currentHost() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        }

        String host = request.getHeader("X-Forwarded-Host");
        if (!StringUtils.isEmpty(host)) {
            return host;
        }
        return request.getHeader("Host");
    }

    public static String currentRemoteIp() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        }

        if (request.getHeader("x-forwarded-for") != null) {
            return request.getHeader("x-forwarded-for");
        }
        return request.getRemoteAddr();
    }

    public static String currentUrl() {
        HttpServletRequest request = currentRequest();
        if (request == null) {
            return "";
        }

        return request.getScheme() + "://" + currentHost() + request.getRequestURI();
    }
}
