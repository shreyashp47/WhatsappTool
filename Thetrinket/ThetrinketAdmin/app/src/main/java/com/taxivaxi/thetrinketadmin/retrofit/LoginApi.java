package com.taxivaxi.thetrinketadmin.retrofit;





import com.taxivaxi.thetrinketadmin.model.login.LoginApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginApi {

    @FormUrlEncoded
    @POST(ApiUrl.login)
    Call<LoginApiResponse> performLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
