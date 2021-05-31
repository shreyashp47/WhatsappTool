package com.trinket.travelparo.retrofitapis;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.model.taxitypemodel.TaxiTypeApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sandeep on 9/10/17.
 */

public interface TaxiTypeAPI {

    @FormUrlEncoded
    @POST(Constant.taxiTypeUrl)
    Call<TaxiTypeApiResponse> getTaxiTypes(@Field("Authorisation") String accessToken,
                                           @Field("admin_id") String adminId);
}
