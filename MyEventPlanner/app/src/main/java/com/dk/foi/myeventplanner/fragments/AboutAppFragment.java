package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.services.EmailSenderService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutAppFragment extends Fragment {
    private EmailSenderService senderService;
    private String fragmentTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_about_app,container,false);
        ButterKnife.bind(this, view);

        fragmentTitle = getResources().getString(R.string.about_app_title);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(fragmentTitle);
        senderService = new EmailSenderService(this.getActivity());
    }

    @OnClick(R.id.button_feedback)
    public void onButtonFeedbackPressed() {
        senderService.sendEmail();
    }
}
