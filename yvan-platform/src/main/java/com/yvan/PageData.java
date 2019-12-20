package com.yvan;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * 说明：参数封装Map
 * 创建人：FH Q313596790
 * 修改时间：2014年9月20日
 */
public class PageData extends HashMap implements Map {

    private static final long serialVersionUID = 1L;

    Map map = null;
    HttpServletRequest request;

    public PageData(HttpServletRequest request) {
        this.request = request;
        Map properties = getRequestPayload(request);
        if (properties.isEmpty()) {
            properties = request.getParameterMap();
        }
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
                returnMap.put(name, value);
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
                returnMap.put(name, value);
            } else {
                returnMap.put(name, valueObj);
            }
        }
        //sessionID:(uuid:用户ID:企业ID:部门ID:部门层级:deecoop:userLoginMap:web)
        String sessionID = request.getHeader("sessionID");
        if (!StringUtils.isEmpty(sessionID)) {
            String[] atrrs = sessionID.split(":");
            if (atrrs.length >= 2) {
                String userId = atrrs[1];
                String companyId = atrrs[2];
                String deptId = atrrs[3];
                String deptLayer = atrrs[4];
                returnMap.put("cuser", userId);
                returnMap.put("uuser", userId);
                returnMap.put("currentUserId", userId);
                returnMap.put("currentCompanyId", companyId);
                returnMap.put("deptRId",deptId);
                returnMap.put("deptLayer",deptLayer);
                returnMap.put("sessionID", sessionID);
            }
        }

        if(returnMap.get("cdate")!=null){
            returnMap.put("cdate",null);
        }
        if(returnMap.get("udate")!=null){
            returnMap.put("udate",null);
        }
        map = returnMap;
    }


    public static Map getRequestPayload(HttpServletRequest req) {

        StringBuilder sb = new StringBuilder();

        try {

            BufferedReader reader = req.getReader();

            char[] buff = new char[1024];

            int len;

            while ((len = reader.read(buff)) != -1) {


                sb.append(buff, 0, len);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }


//		Gson gson = new Gson();
//		Map map = gson.fromJson(sb.toString(), Map.class);
//		JsonWapper jsonWapper = new JsonWapper(sb.toString());
//		Map map = jsonWapper.asObject(Map.class);
        if (StringUtils.isEmpty(sb.toString())) {
            return new HashMap();
        }
        Map map = YvanUtil.jsonToObj(sb.toString(), Map.class);
        return map == null ? new HashMap() : map;

    }


    public PageData() {
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        return get(key) == null ? "" : get(key).toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    public Object putQueryStr(Object value) {
        return map.put("queryStr", value);
    }


    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(Map t) {
        map.putAll(t);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        return map.values();
    }

}
