package com.taxivaxi.spoc.retrofit;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import com.taxivaxi.spoc.model.taxibookingmodel.TaxiBookingApiResponse;
import com.taxivaxi.spoc.model.taxidetailsmodels.TaxiBookingDetailsApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sandeep on 25/9/17.
 */

public interface TaxiBookingsAPI {

    @FormUrlEncoded
    @POST(Constant.taxiBookingsURL)
    Call<TaxiBookingApiResponse> getAllTaxiBookings(@Field("access_token") String accessToken,
                                                    @Field("booking_type") String bookingType,
                                                    @Field("page_no") String pageNo);

    @FormUrlEncoded
    @POST(Constant.taxiBookingDetailsURL)
    Call<TaxiBookingDetailsApiResponse> getTaxiBookingDetails(@Field("access_token") String accessToken,
                                                              @Field("booking_id") String bookingId);

    @FormUrlEncoded
    @POST(Constant.rejectTaxiBookingURL)
    Call<RejectBookingApiResponse> rejectTaxiBooking(@Field("access_token") String accessToken,
                                                     @Field("booking_id") String bookingId);



}
