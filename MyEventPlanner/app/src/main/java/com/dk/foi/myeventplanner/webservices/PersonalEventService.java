package com.dk.foi.myeventplanner.webservices;

import android.os.AsyncTask;

import com.dk.foi.data.entities.Event;
import com.dk.foi.myeventplanner.helpers.MyJsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalEventService {

    private MyJsonParser parser;

    public PersonalEventService() {
        parser = new MyJsonParser();
    }

    public List<Event> getAll(String userId) {
        String result = "";
        PersonalEventGetter red = new PersonalEventGetter(userId);
        try{
            result = red.execute().get().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return parser.ParseEventsInfo(result);
    }

    public void create(String userId, Event event) {
        PersonalEventAdded eventAdder = new PersonalEventAdded(userId, event);
        try{
            eventAdder.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String userId, String eventId) {
        PersonalEventDelete eventDeleter = new PersonalEventDelete(userId, eventId);
        try{
            eventDeleter.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Inner static classes which support service methods

    private static class PersonalEventGetter extends AsyncTask<String,Void,String> {
        final private String queryId = "1";
        private String userId;
        private String result;

        private PersonalEventGetter(String userId) { this.userId = userId; }

        private String getEventsInfo(){
            String res = "";
            try {

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("queryId", queryId)
                        .add("userId", userId)
                        .build();

                Request request = new Request.Builder()
                        .url("http://host/eventsQuery.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                res = response.body().string();

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = getEventsInfo();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
    }

    private static class PersonalEventAdded extends AsyncTask<String,Void,Void> {
        final String queryId = "3";
        String userId;
        Event chosenEvent = new Event();

        private PersonalEventAdded(String userId, Event event) {
            this.userId = userId;
            this.chosenEvent = event;
        }

        private void addNewEvent() {
            String eventId = String.valueOf(chosenEvent.getId());
            String eventType = String.valueOf(chosenEvent.getType());
            String eventName = chosenEvent.getName();
            String eventDate = chosenEvent.getDate();
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("queryId", queryId)
                        .add("userId", userId)
                        .add("eventId", eventId)
                        .add("eventType", eventType)
                        .add("eventName", eventName)
                        .add("eventDate", eventDate)
                        .build();

                Request request = new Request.Builder()
                        .url("http://host/eventsQuery.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                addNewEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class PersonalEventDelete extends AsyncTask<String,Void,Void> {
        final String queryId = "2";
        String userId;
        String eventId;

        private PersonalEventDelete(String userId, String eventId) {
            this.userId = userId;
            this.eventId = eventId;
        }

        private void deleteChosenEvent(){
            try {

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("queryId", queryId)
                        .add("userId", userId)
                        .add("eventId", eventId)
                        .build();

                Request request = new Request.Builder()
                        .url("http://host/eventsQuery.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                deleteChosenEvent();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
