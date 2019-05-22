package com.example.myapplication.view.extras;

import java.util.Date;

public class DateHelper {

    private static final String F_SLASH = "/";

    public static Date obtainDateFromString(String date){
        String[] ddmmyyyy = date.split(F_SLASH);
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1])-1;
        int year = Integer.parseInt(ddmmyyyy[2])-1900;
        return new Date(year, month, day);
    }
}
