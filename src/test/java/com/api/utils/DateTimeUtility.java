package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtility {

    private  DateTimeUtility(){

    }
    public static String getTimeWithDaysAge(int days){
        return Instant.now().minus(days, ChronoUnit.DAYS).toString();
    }
}
