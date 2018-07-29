package com.dk.foi.myeventplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.dk.foi.myeventplanner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editText_username)
    EditText username;

    @BindView(R.id.editText_password)
    EditText password;

    private String login_name;
    private String login_pass;
    private String loginResponseSuccess;
    private String loginResponseError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginResponseSuccess = getResources().getString(R.string.login_success);
        loginResponseError = getResources().getString(R.string.login_error);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button_login)
    public void onLoginButtonClicked(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
        this.finish();

        // TODO
    }
}
