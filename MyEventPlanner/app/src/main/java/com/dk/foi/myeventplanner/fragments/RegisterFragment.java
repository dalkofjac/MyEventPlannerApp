package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.foi.data.entities.User;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.helpers.DateManager;
import com.dk.foi.myeventplanner.webservices.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {
    @BindView(R.id.editText_reg_name)
    EditText regName;

    @BindView(R.id.editText_reg_surname)
    EditText regSurname;

    @BindView(R.id.editText_reg_email)
    EditText regEmail;

    @BindView(R.id.editText_reg_username)
    EditText regUsername;

    @BindView(R.id.editText_reg_password)
    EditText regPassword;

    private String fragmentTitle;
    private String regSuccess;
    private String regError;
    private String regUserAlreadyExists;
    private String regErrorGeneral;

    private UserService userService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_registration,container,false);
        ButterKnife.bind(this, view);

        fragmentTitle = getResources().getString(R.string.register_title);

        regSuccess = getResources().getString(R.string.register_success);
        regError = getResources().getString(R.string.register_error);
        regUserAlreadyExists = getResources().getString(R.string.register_user_exists);
        regErrorGeneral = getResources().getString(R.string.register_error_general);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(fragmentTitle);

        userService = new UserService();
    }
    @OnClick(R.id.button_register)
    public void onRegisterButtonClicked(){
        if(inputCheck() == true){
            String response = "";
            User user = new User();
            user.setName(regName.getText().toString());
            user.setSurname(regSurname.getText().toString());
            user.setEmail(regEmail.getText().toString());
            user.setCreated(DateManager.getTodayDate());

            response = userService.create(user, regUsername.getText().toString(), regPassword.getText().toString());

            if(response.equals("pass")){
                getActivity().onBackPressed();
                Toast.makeText(this.getActivity().getApplicationContext(),regSuccess,Toast.LENGTH_LONG).show();
            }
            else if(response.equals("stop")){
                Toast.makeText(this.getActivity().getApplicationContext(),regUserAlreadyExists,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this.getActivity().getApplicationContext(),regErrorGeneral,Toast.LENGTH_LONG).show();
            }
        }
        else{
            regName.setText("");
            regSurname.setText("");
            regEmail.setText("");
            regUsername.setText("");
            regPassword.setText("");
            Toast.makeText(this.getActivity().getApplicationContext(),regError,Toast.LENGTH_LONG).show();
        }
    }
    private boolean inputCheck(){
        if(regName.getText().length()< 2
                || regSurname.getText().length()< 2
                || regEmail.getText().length() < 5
                || regUsername.getText().length() < 3
                || regPassword.getText().length() < 3){
            return false;
        }
        else if(regName.getText().length()> 50
                || regSurname.getText().length()> 50
                || regEmail.getText().length()> 50
                || regUsername.getText().length()> 20
                || regPassword.getText().length()> 20){
            return false;
        }
        else if(!checkForIllegalChars()){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkForIllegalChars(){
        String[] badChars = new String[]{" ", "/", "#", "="};
        for(int i=0;i<badChars.length;i++){
            if(regUsername.getText().toString().contains(badChars[i])|| regPassword.getText().toString().contains(badChars[i])||
                    regName.getText().toString().contains(badChars[i])|| regSurname.getText().toString().contains(badChars[i])||
                    regEmail.getText().toString().contains(badChars[i])){
                return false;
            }
        }
        if(!regEmail.getText().toString().contains("@")){
            return false;
        }
        return true;
    }
}
