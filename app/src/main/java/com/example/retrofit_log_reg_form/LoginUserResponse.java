package com.example.retrofit_log_reg_form;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {
    @SerializedName("response")
    @Expose()
    public Loginresponse loginresponse;

    public Loginresponse getLoginresponse() {
        return loginresponse;
    }
}
