package com.taxivaxi.operator.retrofit;


import com.taxivaxi.operator.model.login.LoginApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginApi {

    @FormUrlEncoded
    @POST(ApiURLs.loginURL)
    Call<LoginApiResponse> performLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
