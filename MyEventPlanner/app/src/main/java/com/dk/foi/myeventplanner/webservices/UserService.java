package com.dk.foi.myeventplanner.webservices;

import android.os.AsyncTask;

import com.dk.foi.data.entities.User;
import com.dk.foi.myeventplanner.helpers.MyJsonParser;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService {

    private final static String BASE_URL = "http://myeventplannerweb.000webhostapp.com/";
    private final static String DIRECTORY_PATH = "webservices/";

    private MyJsonParser parser;

    public UserService() {
        parser = new MyJsonParser();
    }

    public String checkLogin(String username, String password) {
        String response = "";
        LoginChecker loginChecker = new LoginChecker(username, password);
        try{
            response = loginChecker.execute().get().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public User get(String userId) {
        String result = "";
        UserDataGetter receiveUserData = new UserDataGetter(userId);
        try{
            result = receiveUserData.execute().get().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return parser.ParseUserInfo(result);
    }

    public String create(User user, String username, String password) {
        String result = "";
        UserAdder userAdder = new UserAdder(user, username, password);
        try{
            result = userAdder.execute().get().toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // Inner classes which support service methods

    private static class LoginChecker extends AsyncTask<String,Void,String> {
        final private String SCRIPT_NAME = "check_login.php";

        private String username;
        private String password;

        private String result;

        private LoginChecker(String username, String password) {
            this.username = username;
            this.password = password;
        }

        private String checkLoginData(){
            String res = "";
            try {

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("username", username)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url(BASE_URL + DIRECTORY_PATH + SCRIPT_NAME)
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
                result = checkLoginData();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
    }

    private static class UserDataGetter extends AsyncTask<String,Void,String> {
        final private String SCRIPT_NAME = "get_user.php";

        private String userId;
        private String result;

        private UserDataGetter(String userId) {
            this.userId = userId;
        }

        private String getUserInfo() {
            String res = "";
            try {

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("userId", userId)
                        .build();

                Request request = new Request.Builder()
                        .url(BASE_URL + DIRECTORY_PATH + SCRIPT_NAME)
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                res = response.body().string();

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = getUserInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    private static class UserAdder extends AsyncTask<String, Void, String> {
        final private String SCRIPT_NAME = "add_user.php";

        private User user;
        private String username;
        private String password;

        private String result;

        private UserAdder(User user, String username, String password) {
            this.user = user;
            this.username = username;
            this.password = password;
        }

        private String addNewUser(){
            String res = "";
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("regName", user.getName())
                        .add("regSurname", user.getSurname())
                        .add("regEmail", user.getEmail())
                        .add("regUsername", username)
                        .add("regPassword", password)
                        .add("regCreated", user.getCreated())
                        .build();

                Request request = new Request.Builder()
                        .url(BASE_URL + DIRECTORY_PATH + SCRIPT_NAME)
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
                result = addNewUser();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
    }
}
