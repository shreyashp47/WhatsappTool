package com.cotrav.testalarm.retrofit;

import com.cotrav.testalarm.data.LoginApiResponce;
import com.cotrav.testalarm.data.UploadLeadResponce;
import com.cotrav.testalarm.data.phoneno.PhoneResponce;
import com.cotrav.testalarm.data.upload.UploadResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PhoneNumAPI {


    @GET("http://novuslogic.in/AAH/PHP-Slim-Restful/api/getLeads")
    Call<PhoneResponce> getPhoneNumber();

    @Headers({"Content-Type: application/json"})
    @POST("http://novuslogic.in/AAH/PHP-Slim-Restful/api/getLeadbyuser")
    Call<PhoneResponce> getPhoneNumbers(@Body JsonObject locationPost);

    @POST("http://novuslogic.in/AAH/PHP-Slim-Restful/api/insertCall")
    Call<UploadResponse> upload(@Body JsonObject locationPost);


    @Headers({"Content-Type: application/json"})
    @POST("http://novuslogic.in/AAH/PHP-Slim-Restful/api/login")
    Call<LoginApiResponce> login(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @POST("http://novuslogic.in/AAH/PHP-Slim-Restful/api/insertCall")
    Call<UploadLeadResponce> insertCall(@Body JsonArray locationPost);


}
