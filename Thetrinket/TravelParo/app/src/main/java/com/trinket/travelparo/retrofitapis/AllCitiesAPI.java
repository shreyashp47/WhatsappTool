package com.taxivaxi.spoc.retrofit;

import com.taxivaxi.spoc.model.allcitiesmodel.AllCitiesApiResponse;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCityApiResponse;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssessmentCodeApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sandeep on 17/7/17.
 */

public interface AllCitiesAPI {
    @FormUrlEncoded
    @POST("getAllCities")
    Call<AllCitiesApiResponse> getCities(@Field("access_token") String access_token,
                                         @Field("admin_id") String admin_id);

    @FormUrlEncoded
    @POST("getAllCities")
    Call<AllCitiesApiResponse> getAllCities(@Field("access_token") String access_token,
                                            // tobeReplaced @Field("admin_id") String admin_id);
                                            @Field("user") String user);
    @FormUrlEncoded
    @POST("getAllAssessmentCities")
    Call<AssCityApiResponse> getAllAssCities(@Field("access_token") String access_token,
                                             @Field("admin_id") String admin_id);

    @FormUrlEncoded
    @POST("hotels/getAllCities")
    Call<AllCitiesApiResponse> getAllHotelCities(@Field("access_token") String access_token,
                                            @Field("admin_id") String admin_id);

    @FormUrlEncoded
    @POST("getAllCities")
    Call<AllCitiesApiResponse> getAllAssessmentCities(@Field("access_token") String access_token,
                                                      @Field("admin_id") String admin_id,
                                                      @Field("user") String user);
    @FormUrlEncoded
    @POST("hotels/getAllCities")
    Call<AllCitiesApiResponse> getAllHotelAssessmentCities(@Field("access_token") String access_token,
                                                      @Field("admin_id") String admin_id,
                                                      @Field("user") String user);


}
