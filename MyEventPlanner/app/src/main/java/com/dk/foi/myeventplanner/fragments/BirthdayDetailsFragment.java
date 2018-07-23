package com.dk.foi.myeventplanner.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.services.EventDataService;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventDetailsFragmentBase;
import com.dk.foi.myeventplanner.services.TimerSetterService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BirthdayDetailsFragment extends EventDetailsFragmentBase {

    public BirthdayDetailsFragment() {
        super(EventType.BIRTHDAY);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.birthday_details_title);
    }
}
