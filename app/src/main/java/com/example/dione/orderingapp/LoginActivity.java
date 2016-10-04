package com.example.dione.orderingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dione.orderingapp.application.OrderingApplication;
import com.example.dione.orderingapp.event.GetLoginResponseEvent;
import com.example.dione.orderingapp.event.SendLoginRequestEvent;
import com.example.dione.orderingapp.event.SendWeatherEventError;
import com.example.dione.orderingapp.helpers.Helpers;
import com.squareup.otto.Subscribe;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private OrderingApplication orderingApplication;
    private Button buttonLogin;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initialize();
        redirectIfLoggedIn();
    }

    private void initialize(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.message_loading));
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        orderingApplication = new OrderingApplication();
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void saveLoginPreference(String key, String value){
        Helpers.saveStringSharedPreference(this, key, value);
    }

    private void redirectIfLoggedIn(){
        if(!Helpers.getStringPreference(this, Constants.USER_FIRSTNAME).equals("")){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }

    private void sendLoginRequest(){
        progressDialog.show();
        orderingApplication.mBus.post(new GetLoginResponseEvent(editTextUsername.getText().toString(), editTextPassword.getText().toString()));
    }

    @Subscribe
    public void onSendLoginEvent(SendLoginRequestEvent sendLoginRequestEvent) {
        if (sendLoginRequestEvent.getmLogin().getMessage().contains("success")){
            saveLoginPreference(Constants.USER_FIRSTNAME, String.valueOf(sendLoginRequestEvent.getmLogin().getResult().getFirstName()));
            saveLoginPreference(Constants.USER_LASTNAME, String.valueOf(sendLoginRequestEvent.getmLogin().getResult().getLastName()));
            saveLoginPreference(Constants.USER_EMAIL, String.valueOf(sendLoginRequestEvent.getmLogin().getResult().getEmailAddress()));
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }else{
            Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }

    @Subscribe
    public void onSendWeatherEventError(SendWeatherEventError sendWeatherEventError) {
//        forecastTextView.setText(sendWeatherEventError.getmRetroFitError().toString());
    }


    @Override
    public void onResume() {
        super.onResume();
        orderingApplication.mBus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        orderingApplication.mBus.unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                if (Helpers.isInputValid(editTextUsername, this) && Helpers.isInputValid(editTextPassword, this)){
                    sendLoginRequest();
                }
                break;
        }
    }


}
