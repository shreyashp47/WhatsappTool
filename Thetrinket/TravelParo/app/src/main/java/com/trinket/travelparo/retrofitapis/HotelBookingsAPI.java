package com.taxivaxi.spoc.retrofit;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.model.hotelbookingdetaismodel.HotelBookingDetailsApiResponse;
import com.taxivaxi.spoc.model.hotelbookingmodel.HotelBookingApiResponse;
import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sandeep on 3/10/17.
 */

public interface HotelBookingsAPI {

    @FormUrlEncoded
    @POST(Constant.hotelBookingURL)
    Call<HotelBookingApiResponse> getHotelBookings(@Field("access_token") String accessToken,
                                                   @Field("booking_type") String bookingType,
                                                   @Field("page_no") String pageNo);

    @FormUrlEncoded
    @POST(Constant.hotelBookingDetailsURL)
    Call<HotelBookingDetailsApiResponse> getHotelBookingDetails(@Field("access_token") String accessToken,
                                                                @Field("booking_id") String bookingId);

    @FormUrlEncoded
    @POST(Constant.rejectHotelBookingURL)
    Call<RejectBookingApiResponse> rejectHotelBooking(@Field("access_token") String accessToken,
                                                      @Field("booking_id") String bookingId);
}
