package com.example.retrofit_log_reg_form;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    //String BaseURL = "http://192.168.43.207/digital_reader/";
    String BaseURL = "https://pixeldev.in/webservices/digital_reader/";

    //login
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginUserResponse> getUserLogin(
            @Field("email") String email,
            @Field("password") String password
    );

}
