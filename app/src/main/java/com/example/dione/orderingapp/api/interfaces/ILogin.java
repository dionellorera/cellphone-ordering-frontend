package com.example.dione.orderingapp.api.interfaces;

import com.example.dione.orderingapp.api.response_models.Login.Login;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by dione on 29 Sep 2016.
 */

public interface ILogin {
    @POST("/user/login")
    @FormUrlEncoded
    void getLogin(@Field("username") String username, @Field("password") String password, Callback<Login> callback);
}
