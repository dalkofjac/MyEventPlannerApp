package com.dk.foi.myeventplanner.helpers;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.entities.User;
import com.dk.foi.data.enums.EventType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyJsonParser {

    public MyJsonParser() { }

    public User ParseUserInfo(String jsonString){
        User currentUser = new User();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("user");
            JSONObject JO = jsonArray.getJSONObject(0);
            currentUser.setId(Integer.parseInt(JO.getString("id")));
            currentUser.setName(JO.getString("name"));
            currentUser.setSurname(JO.getString("surname"));
            currentUser.setEmail(JO.getString("email"));
            currentUser.setCreated(JO.getString("created"));
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return currentUser;
    }
    public List<Event> ParseEventsInfo(String jsonString){
        List<Event> eventsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("event");
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject JO = jsonArray.getJSONObject(i);
                Event currentEvent = new Event();
                currentEvent.setId(Integer.parseInt(JO.getString("id")));
                currentEvent.setType(EventType.fromInteger(Integer.parseInt(JO.getString("type"))));
                currentEvent.setName(JO.getString("name"));
                currentEvent.setDate(JO.getString("date"));
                currentEvent.setCreated(JO.getString("created"));
                eventsList.add(currentEvent);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return eventsList;
    }
}
