package com.example.retrofit_log_reg_form;

import static com.example.retrofit_log_reg_form.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.retrofit_log_reg_form.LoginActivity.USER_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString(USER_ID, "");

        checkData();

    }

    public void checkData() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!user_id.equals("")) {
                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                    finish();
                    startActivity(i);

                } else {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(i);
                }

            }
        }, 2000);


    }

}