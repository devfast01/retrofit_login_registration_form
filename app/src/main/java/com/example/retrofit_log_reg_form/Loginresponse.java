package com.example.retrofit_log_reg_form;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresponse {
    @SerializedName("user_id")
    @Expose()
    public String user_id;


    @SerializedName("first_name")
    @Expose()
    public String username;

    @SerializedName("last_name")
    @Expose()
    public String phoneNumber;

    @SerializedName("email")
    @Expose()
    public String email;


    public String getEmail() {
        return email;
    }


    public String getFirst_name() {
        return username;
    }

    public String getLast_name() {
        return phoneNumber;
    }

    public String getUser_id() {
        return user_id;
    }
}
