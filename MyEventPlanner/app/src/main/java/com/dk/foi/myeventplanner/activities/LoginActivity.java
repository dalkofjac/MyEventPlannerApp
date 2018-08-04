package com.dk.foi.myeventplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.foi.myeventplanner.MainActivity;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.webservices.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editText_username)
    EditText username;

    @BindView(R.id.editText_password)
    EditText password;

    private String loginName;
    private String loginPass;
    private String loginResponseSuccess;
    private String loginResponseError;

    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginResponseSuccess = getResources().getString(R.string.login_success);
        loginResponseError = getResources().getString(R.string.login_error);
        ButterKnife.bind(this);

        service = new UserService();
    }
    @OnClick(R.id.button_login)
    public void onLoginButtonClicked(){
        String response = "";
        boolean isValidResponse = false;
        loginName = username.getText().toString();
        loginPass = password.getText().toString();

        if(loginName.isEmpty() || loginPass.isEmpty()) {
            Toast.makeText(this, loginResponseError, Toast.LENGTH_SHORT).show();
        } else {
            response = service.checkLogin(loginName, loginPass);

            try {
                int num = Integer.parseInt(response);
                isValidResponse = true;
            } catch (NumberFormatException e) {
                isValidResponse = false;
            }

            if(!isValidResponse) {
                Toast.makeText(this, loginResponseError, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, loginResponseSuccess, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, UserActivity.class);
                intent.putExtra("USER_ID", response);
                startActivity(intent);

                MainActivity.getInstance().finish();
                this.finish();
            }
        }
    }
}
