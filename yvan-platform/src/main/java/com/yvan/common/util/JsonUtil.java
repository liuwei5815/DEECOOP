package com.yvan.common.util;

import com.yvan.YvanUtil;

import java.util.Map;

public class JsonUtil {
    public static Map<?, ?> jsonString2Map(String jsonString) {
        return YvanUtil.jsonToMap(jsonString);
    }

    public static Object jsonString2Object(String jsonString, Class entityClass) {
        return YvanUtil.jsonToObj(jsonString, entityClass);
    }
}
