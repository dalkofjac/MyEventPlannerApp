package com.dk.foi.myeventplanner.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.fragments.HolidayDetailsFragment;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;

public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView name;
    public TextView date;

    private Context context;

    public EventsViewHolder(View view , Context context) {
        super(view);

        this.context = context;
        name = view.findViewById(R.id.textView_event_name);
        date = view.findViewById(R.id.textView_event_date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        args.putString("EVENT_NAME",(String)name.getText());
        args.putString("EVENT_DATE",(String)date.getText());

        HolidayDetailsFragment hdf = new HolidayDetailsFragment();
        hdf.setArguments(args);
        FragmentStarter.StartNewFragment(hdf, ((Activity)context), FragmentLevel.LEVEL_TWO);

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
