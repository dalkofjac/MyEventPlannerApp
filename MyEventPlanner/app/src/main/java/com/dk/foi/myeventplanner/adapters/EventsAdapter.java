package com.dk.foi.myeventplanner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    private List<Event> eventList;
    private Context context;
    private EventType type;

    public EventsAdapter(List<Event> eventList, Context context, EventType type) {
        this.eventList = eventList;
        this.context = context;
        this.type = type;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new EventsViewHolder(view, context, type);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.id = event.getId();
        holder.name.setText(event.getName());
        holder.date.setText(event.getDate());
        holder.created = event.getCreated();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
