package com.dk.foi.myeventplanner.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateManager {
    public static Date convertStringToDate(String sDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date convertedDate = new Date();
        try {
            return convertedDate = dateFormat.parse(sDate);
        }catch(ParseException e){
            return null;
        }
    }

    public static String getTodayDate(){
        String tDate = "";
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentYear = cal.get(Calendar.YEAR);
        if(currentDay < 10){
            tDate = tDate + "0" + currentDay + "/";
        }
        else{
            tDate = tDate + currentDay + "/";
        }
        if(currentMonth < 10){
            tDate = tDate + "0" + currentMonth;
        }
        else{
            tDate = tDate + currentMonth;
        }
        tDate = tDate + "/" + currentYear;
        return tDate;
    }

    public static boolean isValidDate(String input) {
        String formatString = "dd/MM/yyyy";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(input);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
