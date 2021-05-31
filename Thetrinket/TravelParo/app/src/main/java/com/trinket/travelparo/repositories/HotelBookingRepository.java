package com.taxivaxi.spoc.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Log;

import com.taxivaxi.spoc.model.hotelbookingdetaismodel.HotelBookingDetailsApiResponse;
import com.taxivaxi.spoc.model.hotelbookingmodel.Booking;
import com.taxivaxi.spoc.model.hotelbookingmodel.HotelBookingApiResponse;
import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.retrofit.HotelBookingsAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sandeep on 3/10/17.
 */

public class HotelBookingRepository {

    MutableLiveData<List<Booking>> currentBooking;
    MutableLiveData<List<Booking>>  upComingBooking;
    MutableLiveData<List<Booking>>  completedBooking;
    MutableLiveData<List<Booking>>  cancelledBooking;
    MutableLiveData<String> error;
    MutableLiveData<String> currentError;
    MutableLiveData<String> detailError;
    MutableLiveData<String> upcomingError;
    MutableLiveData<String> completedError;
    MutableLiveData<String> cancelledError;
    MutableLiveData<String> status;
    HotelBookingsAPI hotelBookingsAPI;
    MutableLiveData<Boolean> hasMoreCurrentBookings;
    MutableLiveData<Boolean> hasMoreUpcomingBookings;
    MutableLiveData<Boolean> hasMoreCompletedBookings;
    MutableLiveData<Boolean> hasMoreCancelledBookings;
    MutableLiveData<String> cancelledStatus;
    MutableLiveData<String> completedStatus;
    MutableLiveData<HotelBookingDetailsApiResponse> hotelBookingDetailsApiResponse;
    MutableLiveData<RejectBookingApiResponse> rejectBookingApiResponse;
    MutableLiveData<String> rejectBookingError;

    public HotelBookingRepository(Application application){
        currentBooking=new MutableLiveData<>();
        upComingBooking =new MutableLiveData<>();
        completedBooking=new MutableLiveData<>();
        cancelledBooking=new MutableLiveData<>();
        hasMoreCurrentBookings=new MutableLiveData<>();
        hasMoreUpcomingBookings=new MutableLiveData<>();
        hasMoreCompletedBookings=new MutableLiveData<>();
        hasMoreCancelledBookings=new MutableLiveData<>();
        hasMoreCurrentBookings.setValue(true);
        hasMoreUpcomingBookings.setValue(true);
        hasMoreCompletedBookings.setValue(true);
        hasMoreCancelledBookings.setValue(true);
        error=new MutableLiveData<>();
        currentError=new MutableLiveData<>();
        detailError=new MutableLiveData<>();
        upcomingError=new MutableLiveData<>();
        completedError=new MutableLiveData<>();
        cancelledError=new MutableLiveData<>();
        rejectBookingError=new MutableLiveData<>();
        rejectBookingApiResponse=new MutableLiveData<>();
        status=new MutableLiveData<>();
        cancelledStatus=new MutableLiveData<>();
        completedStatus=new MutableLiveData<>();
        hotelBookingDetailsApiResponse=new MutableLiveData<>();
        hotelBookingsAPI = ConfigRetrofit.configRetrofit(HotelBookingsAPI.class);

    }

    public MutableLiveData<String> getCurrentError() {
        return currentError;
    }
    public MutableLiveData<String> detailError() {
        return detailError;
    }

    public MutableLiveData<String> getUpcomingError() {
        return upcomingError;
    }

    public MutableLiveData<String> getCompletedError() {
        return completedError;
    }

    public MutableLiveData<String> getCancelledError() {
        return cancelledError;
    }

    public LiveData<List<Booking>> getCancelledBooking() {

        return cancelledBooking;
    }

    public MutableLiveData<RejectBookingApiResponse> getRejectBookingApiResponse() {
        return rejectBookingApiResponse;
    }

    public MutableLiveData<String> getRejectBookingError() {
        return rejectBookingError;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<String> getStatus() {
        return status;
    }

    public MutableLiveData<Boolean> getHasMoreCompletedBookings() {
        return hasMoreCompletedBookings;
    }

    public LiveData<HotelBookingDetailsApiResponse> getHotelBookingDetailsApiResponse() {
        return hotelBookingDetailsApiResponse;
    }

    public MutableLiveData<Boolean> getHasMoreCurrentBookings() {
        return hasMoreCurrentBookings;
    }

    public MutableLiveData<Boolean> getHasMoreUpcomingBookings() {
        return hasMoreUpcomingBookings;
    }

    public MutableLiveData<Boolean> getHasMoreCancelledBookings() {
        return hasMoreCancelledBookings;
    }

    public LiveData<List<Booking>> getCompletedBooking(String accessToken, String pageNo){
        hotelBookingsAPI.getHotelBookings(accessToken,"archived",pageNo).enqueue(new Callback<HotelBookingApiResponse>() {
            @Override
            public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        completedStatus.setValue("1");
                        if (response.body().getResponse().getHasMoreBookings().equals("0")){
                            hasMoreCompletedBookings.setValue(false);
                        }
                        else if (response.body().getResponse().getHasMoreBookings().equals("1")) {
                            hasMoreCompletedBookings.setValue(true);
                        }
                        System.out.println("Total Bookings "+response.body().getResponse().getBookings().size());
                        completedBooking.setValue(response.body().getResponse().getBookings());
                    }
                    else {
                        completedError.setValue(response.body().getError());
                    }
                }else {
                    completedError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                completedError.setValue("Connection Error: "+t.getMessage());
            }
        });
        return completedBooking;
    }

    public LiveData<List<Booking>> getCancelledBooking(String accessToken, String pageNo){
        hotelBookingsAPI.getHotelBookings(accessToken,"rejected",pageNo).enqueue(new Callback<HotelBookingApiResponse>() {
            @Override
            public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        if (response.body().getResponse().getHasMoreBookings().equals("0")){
                            hasMoreCancelledBookings.setValue(false);
                        }
                        else if (response.body().getResponse().getHasMoreBookings().equals("1")) {
                            hasMoreCancelledBookings.setValue(true);
                        }
                        System.out.println("Total Bookings "+response.body().getResponse().getBookings().size());
                        cancelledBooking.setValue(response.body().getResponse().getBookings());
                    }
                    else {
                        cancelledError.setValue(response.body().getError());
                    }
                }else {
                    cancelledError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                cancelledError.setValue("Connection Error: "+t.getMessage());
            }
        });

        return cancelledBooking;
    }


    public LiveData<List<Booking>> getUpcomingBooking(String accessToken, String pageNo){
        Log.d("HasMoreUpcoming",hasMoreUpcomingBookings.getValue()+"");

        hotelBookingsAPI.getHotelBookings(accessToken,"upcoming",pageNo).enqueue(new Callback<HotelBookingApiResponse>() {
            @Override
            public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        if (response.body().getResponse().getHasMoreBookings().equals("0")){
                            hasMoreUpcomingBookings.setValue(false);
                        }
                        else if (response.body().getResponse().getHasMoreBookings().equals("1")) {
                            hasMoreUpcomingBookings.setValue(true);
                        }
                        System.out.println("Total Bookings "+response.body().getResponse().getBookings().size());
                        upComingBooking.setValue(response.body().getResponse().getBookings());
                    }
                    else {
                        upcomingError.setValue(response.body().getError());
                    }
                }else {
                    upcomingError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                upcomingError.setValue("Connection Error: "+t.getMessage());
            }
        });

        return upComingBooking;
    }

    public LiveData<List<Booking>> getCurrentBooking(String accessToken, String pageNo){
        hotelBookingsAPI.getHotelBookings(accessToken,"current",pageNo).enqueue(new Callback<HotelBookingApiResponse>() {
            @Override
            public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        if (response.body().getResponse().getHasMoreBookings().equals("0")){
                            hasMoreCurrentBookings.setValue(false);
                        }
                        else if (response.body().getResponse().getHasMoreBookings().equals("1")) {
                            hasMoreCurrentBookings.setValue(true);
                        }
                        System.out.println("Total Bookings "+response.body().getResponse().getBookings().size());
                        currentBooking.setValue(response.body().getResponse().getBookings());
                    }
                    else {
                        currentError.setValue(response.body().getError());
                    }
                }else {
                    currentError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                currentError.setValue("Connection Error: "+t.getMessage());
            }
        });
        return currentBooking;
    }

    public void getHotelBookingDetails(String accessToken,String bookingId){
        hotelBookingsAPI.getHotelBookingDetails(accessToken,bookingId).enqueue(new Callback<HotelBookingDetailsApiResponse>() {
            @Override
            public void onResponse(Call<HotelBookingDetailsApiResponse> call, Response<HotelBookingDetailsApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1") && response.body().getResponse().getBookingDetail()!=null){
                        hotelBookingDetailsApiResponse.setValue(response.body());
                    }else
                        detailError.setValue(response.body().error);


                }else
                    detailError.setValue(response.body().error);
            }

            @Override
            public void onFailure(Call<HotelBookingDetailsApiResponse> call, Throwable t) {
                detailError.setValue("Connection error");
            }
        });
    }


    public void rejectHotelBooking(String accessToken,String bookingid){
        hotelBookingsAPI.rejectHotelBooking(accessToken,bookingid).enqueue(new Callback<RejectBookingApiResponse>() {
            @Override
            public void onResponse(Call<RejectBookingApiResponse> call, Response<RejectBookingApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1") && response.body().getResponse()!=null){
                        rejectBookingApiResponse.setValue(response.body());
                    }
                    else {
                        rejectBookingError.setValue(response.body().getError());
                    }
                }else {
                    rejectBookingError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<RejectBookingApiResponse> call, Throwable t) {
                rejectBookingError.setValue("Connection Error");
            }
        });
    }
}
