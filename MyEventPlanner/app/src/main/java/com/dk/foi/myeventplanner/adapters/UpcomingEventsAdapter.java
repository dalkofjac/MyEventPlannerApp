package com.dk.foi.myeventplanner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.data.entities.Event;
import com.dk.foi.myeventplanner.R;

import java.util.List;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsViewHolder> {
    private List<Event> eventList;
    private Context context;

    public UpcomingEventsAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public UpcomingEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new UpcomingEventsViewHolder(view, context, eventList);
    }

    @Override
    public void onBindViewHolder(UpcomingEventsViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.id = event.getId();
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
