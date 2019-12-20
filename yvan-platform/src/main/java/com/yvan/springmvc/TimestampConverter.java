package com.yvan.springmvc;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by liuzhuang on 2016/5/31.
 */
public class TimestampConverter implements Converter<String, Timestamp> {
    @Override
    public Timestamp convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return new Timestamp(dateFormat.parse(source).getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}