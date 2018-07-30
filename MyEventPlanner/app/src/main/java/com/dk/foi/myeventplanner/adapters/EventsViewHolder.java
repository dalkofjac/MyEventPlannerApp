package com.dk.foi.myeventplanner.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.fragments.BirthdayDetailsFragment;
import com.dk.foi.myeventplanner.fragments.HolidayDetailsFragment;
import com.dk.foi.myeventplanner.fragments.OtherEventDetailsFragment;
import com.dk.foi.myeventplanner.fragments.PersonalEventDetailsFragment;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;

public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public int id;
    public TextView name;
    public TextView date;

    private Context context;
    private EventType type;

    public EventsViewHolder(View view , Context context, EventType type) {
        super(view);

        this.context = context;
        this.type = type;
        name = view.findViewById(R.id.textView_event_name);
        date = view.findViewById(R.id.textView_event_date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        args.putInt("EVENT_ID", id);
        args.putString("EVENT_NAME", (String)name.getText());
        args.putString("EVENT_DATE", (String)date.getText());

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
            case PERSONAL:
                PersonalEventDetailsFragment pdf = new PersonalEventDetailsFragment();
                pdf.setArguments(args);
                FragmentStarter.StartNewFragment(pdf, ((Activity)context), FragmentLevel.LEVEL_TWO);
                break;
            default: break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
