package com.taxivaxi.operator.retrofit;


import com.taxivaxi.operator.model.archivedbooking.BookingApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface UploadDutySlipAPI {

    @FormUrlEncoded
    @POST(ApiURLs.uploadDutySlip)
    Call<BookingApiResponse> uploadDutySlip(@Field("access_token") String accessToken,
                                            @Field("booking_id") String booking_id,
                                            @Field("img_string") String img_string,
                                            @Field("img_ext") String img_ext

    );
}
