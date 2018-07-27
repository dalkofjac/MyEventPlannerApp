package com.dk.foi.myeventplanner.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.fragments.BirthdayDetailsFragment;
import com.dk.foi.myeventplanner.fragments.HolidayDetailsFragment;
import com.dk.foi.myeventplanner.fragments.OtherEventDetailsFragment;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;

import java.util.List;

public class UpcomingEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView name;
    public TextView date;

    private Context context;
    private List<Event> eventList;

    public UpcomingEventsViewHolder(View view , Context context, List<Event> eventList) {
        super(view);

        this.context = context;
        this.eventList = eventList;
        name = view.findViewById(R.id.textView_event_name);
        date = view.findViewById(R.id.textView_event_date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        args.putString("EVENT_NAME",(String)name.getText());
        args.putString("EVENT_DATE",(String)date.getText());

        EventType type = getEventType(eventList, (String)name.getText(), (String)date.getText());

        switch (type) {
            case HOLIDAY:
                HolidayDetailsFragment hdf = new HolidayDetailsFragment();
                hdf.setArguments(args);
                FragmentStarter.StartNewFragment(hdf, ((Activity)context), FragmentLevel.LEVEL_TWO);
                break;
            case BIRTHDAY:
                BirthdayDetailsFragment bdf = new BirthdayDetailsFragment();
                bdf.setArguments(args);
                FragmentStarter.StartNewFragment(bdf, ((Activity)context), FragmentLevel.LEVEL_TWO);
                break;
            case OTHER:
                OtherEventDetailsFragment odf = new OtherEventDetailsFragment();
                odf.setArguments(args);
                FragmentStarter.StartNewFragment(odf, ((Activity)context), FragmentLevel.LEVEL_TWO);
                break;
            default: break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    private EventType getEventType(List<Event> eventList, String name, String date) {
        for(int i = 0; i< eventList.size(); i++) {
            if(eventList.get(i).getName().equals(name)
                    && eventList.get(i).getDate().equals(date)) {
                return eventList.get(i).getType();
            }
        }
        return EventType.OTHER;
    }
}
