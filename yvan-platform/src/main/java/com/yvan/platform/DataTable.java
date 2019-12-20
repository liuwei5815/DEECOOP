package com.yvan.platform;

import java.util.ArrayList;

public class DataTable extends ArrayList<DataRow> {

    public DataRow findByField(String id, Object value) {
        if (value == null) return null;
        for (DataRow data : this) {
            if (data != null && value.equals(data.get(id))) {
                return data;
            }
        }
        return null;
    }
}
