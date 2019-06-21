package com.example.myapplication.view.extras;

import java.util.Calendar;
import java.util.Date;


@SuppressWarnings({"deprecation"})
public class DateHelper {

    public static final String F_SLASH = "/";
    public static final String ZERO = "0";

    public static Date obtainDateFromString(String date){
        String[] ddmmyyyy = date.split(F_SLASH);
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1])-1;
        int year = Integer.parseInt(ddmmyyyy[2])-1900;
        return new Date(year, month, day);
    }

    public static String toStringDDMMYYYY(Date date) {
        return (date.getDate()) + F_SLASH + (date.getMonth()+1) + F_SLASH + (date.getYear()+1900);
    }

    public static String toStringDDMM(Date date) {
        return (date.getDate()) + F_SLASH + (date.getMonth()+1);
    }

    public static String getTodayDate() {
        return toStringDDMMYYYY(Calendar.getInstance().getTime());
    }

    public static String toStringDDMMYYYY(int day, int month, int year) {
        return twoDigits(day) + F_SLASH + twoDigits( (month+1) ) + F_SLASH + year;
    }

    private static String twoDigits(int n) {
        return (n<=9) ? (ZERO + n) : String.valueOf(n);
    }
}
