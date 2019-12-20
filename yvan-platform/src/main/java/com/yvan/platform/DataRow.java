package com.yvan.platform;

import com.yvan.Conv;
import com.yvan.springmvc.ResultModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 数据行
 * 所有列名都被转换成了小写
 * Created by luoyifan on 2016/3/29.
 */
public class DataRow extends ResultModel {

    public DataRow() {
    }

    public DataRow(Map<? extends String, ?> innerMap) {
        this.putAll(innerMap);
    }

    public long asLong(String colName) {
        return Conv.NL(get(colName));
    }

    public String asStr(String colName) {
        return Conv.NS(get(colName));
    }

    public int asInt(String colName) {
        return Conv.NI(get(colName));
    }

    public Date asDate(String colName) {
        return Conv.NDT(get(colName));
    }

    public boolean asBool(String colName) {
        return Conv.NB(get(colName));
    }

    public double asDouble(String colName) {
        return Conv.NDB(get(colName));
    }

    public float asFloat(String colName) {
        return Conv.NFloat(get(colName));
    }

    public short asShort(String colName) {
        return Conv.NShort(get(colName));
    }

    public BigDecimal asBigDec(String colName) {
        return Conv.NDec(get(colName));
    }

    @Override
    public ResultModel put(String key, Object value) {
        return super.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return super.get(key.toString());
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value.toString());
    }

    public DataRow asMap(String colName) {
        return new DataRow((Map<String, Object>) this.get(colName));
    }
}
