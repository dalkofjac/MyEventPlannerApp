package com.dk.foi.myeventplanner.fragments.base;

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
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.services.TimerSetterService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class EventDetailsFragmentBase extends Fragment {
    private final String defaultFragmentTitle = "Time until event";

    @BindView(R.id.textView_event_name)
    TextView textName;

    @BindView(R.id.textView_event_date)
    TextView textDate;

    @BindView(R.id.textView_event_timer)
    TextView textTimer;

    @BindView(R.id.textView_event_days)
    TextView textDays;

    @BindView(R.id.textView_event_created)
    TextView textCreated;

    @BindView(R.id.textView_event_left_general)
    TextView textSubTitle;

    private AlertDialog alertDialog;
    private TimerSetterService timerSetter;

    private EventType eventType;

    private String removalQuestion;
    private String remove;
    private String cancel;
    private String eventEndText;
    private String subTitle;

    protected Event currentEvent;

    public EventDetailsFragmentBase(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_event_details,container,false);
        ButterKnife.bind(this, view);
        alertDialog = new AlertDialog.Builder(view.getContext()).create();

        removalQuestion = view.getContext().getString(R.string.removal_question);
        remove = view.getContext().getString(R.string.remove);
        cancel = view.getContext().getString(R.string.cancel);
        eventEndText = view.getContext().getString(R.string.event_end_text);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getFragmentTitle());
        timerSetter = new TimerSetterService();

        currentEvent = loadCurrentEvent();

        textName.setText(currentEvent.getName());
        textDate.setText(currentEvent.getDate());
        textDays.setText(""+ timerSetter.calculateDays(currentEvent.getDate()));
        timerSetter.setTimer(currentEvent.getDate(), textTimer, eventEndText);
        textCreated.setText(currentEvent.getCreated());

        setFragmentSubtitle();
        textSubTitle.setText(subTitle);
    }

    @OnClick(R.id.button_event_delete)
    public void onButtonEventDeleteClicked(){
        alertDialog.setTitle(removalQuestion);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCurrentEvent();
                alertDialog.dismiss();
                getActivity().onBackPressed();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    protected void deleteCurrentEvent() {
        Event event = currentEvent;
        event.delete();
    }

    protected String getFragmentTitle() {
        return defaultFragmentTitle;
    }

    private Event loadCurrentEvent() {
        Event event = new Event();
        event.setId(getArguments().getInt("EVENT_ID"));
        event.setName(getArguments().getString("EVENT_NAME"));
        event.setDate(getArguments().getString("EVENT_DATE"));
        event.setCreated(getArguments().getString("EVENT_CREATED"));
        return event;
    }

    private void setFragmentSubtitle() {
        switch (eventType) {
            case HOLIDAY:
                subTitle = getResources().getString(R.string.event_details_subtitle_HOLIDAY);
                break;
            case BIRTHDAY:
                subTitle = getResources().getString(R.string.event_details_subtitle_BIRTHDAY);
                break;
            case OTHER:
                subTitle = getResources().getString(R.string.event_details_subtitle_OTHER);
                break;
            case PERSONAL:
                subTitle = getResources().getString(R.string.event_details_subtitle_PERSONAL);
            default:
                subTitle = getResources().getString(R.string.event_details_subtitle_OTHER);
                break;
        }
    }
}
