package com.dk.foi.myeventplanner.services;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.dk.foi.myeventplanner.helpers.DateManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerSetterService {
    private static final String FORMAT = "%02d:%02d:%02d";

    /**
     * Method used to set timer Text Box on details fragment screen
     * It counts down remaining time (seconds) to certain event
     * @param eventDate date of event
     * @param textTimer textView for timer
     * @param endText text which will be shown when times reaches zero (0)
     */
    public void setTimer(String eventDate, final TextView textTimer, final String endText){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        sdf.setLenient(false);

        try {
            Date endDate = sdf.parse(eventDate);
            Date startDate = new Date();

            long diffInMs = endDate.getTime() - startDate.getTime();

            new CountDownTimer(diffInMs, 1000) {

                public void onTick(long millisUntilFinished) {

                    textTimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + " s");
                }

                public void onFinish() {
                    textTimer.setText(endText);
                }
            }.start();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that calculates exact time remaining to an event based on current date
     * @param eventDate event data
     * @return remaining time
     */
    public long calculateDays(String eventDate){
        long result = 0;
        Date date = DateManager.convertStringToDate(eventDate);
        Calendar event = Calendar.getInstance();
        event.setTime(date);

        long msDiff = Calendar.getInstance().getTimeInMillis() - event.getTimeInMillis();
        result = TimeUnit.MILLISECONDS.toDays(msDiff);

        if(msDiff >= 0) {
            return 0;
        }else{
            return (result*(-1))+1;
        }
    }
}
