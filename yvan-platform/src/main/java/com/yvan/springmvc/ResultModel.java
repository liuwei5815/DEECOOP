package com.yvan.springmvc;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.Conv;
import com.yvan.platform.Pagin;

import java.util.LinkedHashMap;

/**
 * 用来快速返回结果的model对象
 */
public class ResultModel extends LinkedHashMap<String, Object> {

    public ResultModel(){
        set("code", 0);
        set("msg", "执行成功!");
    }

    public ResultModel putMsg(String msg) {
        set("msg", msg);
        return this;
    }

    public ResultModel putTitle(String title) {
        return set("title", title);
    }

    public ResultModel putData(Object data) {
        return set("data", data);
    }

    public Object getData() {
        return get("data");
    }


    public ResultModel putCode(Object code) {
        set("code", code);
        return this;
    }

    public Object getCode() {
        get("code");
        return this;
    }

    public ResultModel putResult(Object result) {
        set("result", result);
        return this;
    }

    public Object getResult() {
        get("result");
        return this;
    }


    public ResultModel putTotal(int total) {
        return set("total", total);
    }

    public ResultModel putTotal(long total) {
        return set("total", total);
    }

    public ResultModel putRows(Object rows) {
        return set("rows", rows);
    }

    public ResultModel success(boolean success) {
        return set("success", success);
    }

    public boolean isSuccess() {
        return Conv.NB(get("success"));
    }

    public ResultModel set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public ResultModel put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResultModel setPagionation(Pagination pagionation, Object data) {
        this.set("list", data);
        this.set("pageSize", pagionation.getSize());
        this.set("pageNo", pagionation.getCurrent());
        this.set("next", pagionation.hasNext() ? pagionation.getCurrent() + 1 : pagionation.getCurrent());
        this.set("last", pagionation.getPages());
        this.set("first", 1);
        this.set("prev", pagionation.hasPrevious() ? pagionation.getCurrent() - 1 : pagionation.getCurrent());
        this.set("count", pagionation.getTotal());
        //"pageSize":20,"pageNo":1,"next":2,"last":2,"first":1,"prev":1
        return this;
    }

    public static ResultModel newSuccess() {
        return new ResultModel().success(true);
    }

    public static ResultModel newSuccess(final Object data) {
        return new ResultModel().success(true).putData(data);
    }

    public static ResultModel newFail(final String errorMsg) {
        return new ResultModel().success(false).putMsg(errorMsg);
    }

    public static ResultModel newBootstrapPaginationData(final Pagin pagination, final Object data) {
        return new ResultModel().success(true).putTotal(pagination.getCount()).putRows(data);
    }

    public static Object newEasyuiResult(final long total, final Object rows) {
        return new ResultModel().putTotal(total).putRows(rows);
    }

    public static Object newEasyuiResult(final int total, final Object rows) {
        return new ResultModel().putTotal(total).putRows(rows);
    }
}
