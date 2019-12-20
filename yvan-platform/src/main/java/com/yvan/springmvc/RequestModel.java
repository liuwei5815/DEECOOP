package com.yvan.springmvc;

import com.yvan.platform.JsonWapper;
import com.yvan.platform.Pagin;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * 接收请求用的model对象
 * Created by luoyifan on 2017/3/5.
 */
public class RequestModel extends LinkedHashMap<String, Object> {

    public static RequestModel newBootstrapTableParam(JsonWapper jw) throws IOException {
        int limit = jw.asInt("limit");
        int offset = jw.asInt("offset");
        String order = jw.asStr("order");
        String sort = jw.asStr("sort");
        String search = jw.asStr("search");

        if (!StringUtils.isEmpty(search)) {
            if (search.startsWith("!")) {
                search = search.replace("!", "");
            } else if (search.contains("%") || search.contains("?")) {
                ;
            } else {
                search = "%" + search + "%";
            }
        }

        if (offset < 0) offset = 0;
        if (limit <= 0) limit = 10;

        RequestModel rm = new RequestModel();

        Pagin pagination = new Pagin(false);
        pagination.setBegin(offset);
        pagination.setPageSize(limit);

        rm.setPagination(pagination);
        rm.put("order", order);
        rm.put("sort", sort);
        rm.put("search", search);

        return rm;
    }

    public Pagin getPagination() {
        return (Pagin) this.get("pagination");
    }

    public void setPagination(Pagin pagination) {
        this.put("pagination", pagination);
    }
}
