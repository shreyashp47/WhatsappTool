package com.taxivaxi.operator.retrofit;



import com.taxivaxi.operator.model.archivedbooking.BookingApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface DutySlipAPI {

    @FormUrlEncoded
    @POST(ApiURLs.getPendingDutySlipBookings)
    Call<BookingApiResponse> getPendingDutySlip(@Field("access_token") String accessToken);
}
