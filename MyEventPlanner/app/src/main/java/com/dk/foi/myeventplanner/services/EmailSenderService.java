package com.dk.foi.myeventplanner.services;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.dk.foi.myeventplanner.R;

public class EmailSenderService {
    private final String devEmailAddress = "info.myeventplanner@gmail.com";
    private final String emailTitle = "MyEventPlanner - feedback";
    private String chooseClient;
    private String errorMsg;
    private Activity activity;

    public EmailSenderService(Activity activity) {
        this.activity = activity;
        chooseClient =  activity.getString(R.string.choose_email_client);
        errorMsg = activity.getString(R.string.email_error_msg);
    }

    public void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{devEmailAddress});
        i.putExtra(Intent.EXTRA_SUBJECT, emailTitle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            activity.startActivity(Intent.createChooser(i, chooseClient));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show();
        }
    }
}
