package com.taxivaxi.approver.retrofit;

import com.taxivaxi.approver.models.analyticsmodel.AnalyticsApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.taxivaxi.approver.retrofit.APIurls.AUTH_ONE_ANALYTICS;
import static com.taxivaxi.approver.retrofit.APIurls.AUTH_TWO_ANALYTICS;

/**
 * Created by sandeep on 21/7/17.
 */

public interface AnalyticsAPI {

    @FormUrlEncoded
    @POST(AUTH_ONE_ANALYTICS)
    Call<AnalyticsApiResponse> getAuthOneAnalytics(@Field("access_token") String access_token);

    @FormUrlEncoded
    @POST(AUTH_TWO_ANALYTICS)
    Call<AnalyticsApiResponse> getAuthtwoAnalytics(@Field("access_token") String access_token);

}
