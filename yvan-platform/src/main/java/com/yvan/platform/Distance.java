package com.yvan.platform;

import java.math.BigDecimal;

public class Distance {

    public static String getDistanceText(Integer distance){
        if(distance == null){
            return  "";
        }

        if(distance >= 1000){
            BigDecimal b = new BigDecimal((float) distance/1000);
            Double result = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
            return result+"km";
        }
        return distance+"m";
    }
}
