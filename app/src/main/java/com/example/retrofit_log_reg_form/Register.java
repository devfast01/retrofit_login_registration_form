package com.example.retrofit_log_reg_form;


import static com.example.retrofit_log_reg_form.API.BaseURL;
import static com.example.retrofit_log_reg_form.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.retrofit_log_reg_form.LoginActivity.USER_ID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends AppCompatActivity {
    private EditText et_username, et_phone, et_email, et_password;
    private FloatingActionButton btnregister;
    private TextView tvlogin;
    String username;
    String phoneNumber;
    String email;
    String password;
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "username";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Pattern pattern_pwd = Pattern.compile("^[a-zA-Z0-9]+$");

    public Register() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_register);

        et_username = (EditText) findViewById(R.id.username);
        et_email = (EditText) findViewById(R.id.email_adr);
        et_phone = (EditText) findViewById(R.id.phone);
        et_password = (EditText) findViewById(R.id.password);
        btnregister = findViewById(R.id.ok);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                email = et_email.getText().toString();
                phoneNumber = et_phone.getText().toString();
                password = et_password.getText().toString();

                Log.d("userdata", "onClick: " + email + password);
                registerMe();
                //ata mla sang kai karu mla response blank yet ahi
                //response server ka bata pehle
                if (!username.isEmpty()) {
                    if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (!email.isEmpty() && Patterns.PHONE.matcher(phoneNumber).matches()) {

                            if (!password.isEmpty() && pattern_pwd.matcher(password).matches()) {

                            } else {
                                Toast.makeText(Register.this, "Enter the Valid Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Enter the Valid Phone Number ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Enter the Valid Email", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(Register.this, "Enter the Valid username", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.ic_gradient_bg);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    private void registerMe() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<RegsiterResponse> call = api.getUserRegi(username, email, phoneNumber, password);
        call.enqueue(new Callback<RegsiterResponse>() {
            @Override
            public void onResponse(Call<RegsiterResponse> call, Response<RegsiterResponse> response) {
                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("responseLog", response.body().getResponse());
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().getResponse();
                        try {
                            parseRegData(jsonresponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegsiterResponse> call, Throwable t) {
                Log.d("go", "onFailure: " + t.toString());
            }
        });
    }


    private void parseRegData(String response) throws JSONException {
        Log.d("juststring", response);
        if (response.equals("success")) {
            Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, LoginActivity.class);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(Register.this, "OOPS", Toast.LENGTH_SHORT).show();
        }
    }

//    private void saveInfo(String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (jsonObject.getString("response").contains("username")) {
//                JSONArray dataArray = jsonObject.getJSONArray("response");
//                for (int i = 0; i < dataArray.length(); i++) {
//                    JSONObject dataobj = dataArray.getJSONObject(i);
//                    Log.d("TAG", "saveInfo: " + dataobj.toString());
//
//                    sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
//                    editor = sharedPreferences.edit();
//                    editor.putString(USER_ID, dataobj.getString("user_id"));
//                    editor.putString(USER_NAME, dataobj.getString("username"));
//                    editor.putString(EMAIL, dataobj.getString("email"));
//                    editor.putString(PASSWORD, dataobj.getString("password"));
//                    editor.apply();
//                    Log.d("kon", "saveInfo: " + USER_NAME);
//
//                }
//                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}

