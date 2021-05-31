package com.taxivaxi.spoc.retrofit;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssessmentCodeApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sandeep on 17/7/17.
 */

public interface AssessmentCodeAPI {

    @FormUrlEncoded
    @POST("getAllCodes")
    Call<AssessmentCodeApiResponse> getAssessmentCodes(@Field("access_token") String access_token,
                                                       @Field("admin_id") String adnin_id);
}
