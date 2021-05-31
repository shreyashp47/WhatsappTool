package com.trinket.travelparo.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import com.taxivaxi.spoc.model.taxibookingmodel.Booking;
import com.taxivaxi.spoc.model.taxibookingmodel.TaxiBookingApiResponse;
import com.taxivaxi.spoc.model.taxidetailsmodels.TaxiBookingDetailsApiResponse;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.retrofit.TaxiBookingsAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sandeep on 25/9/17.
 */

public class TaxiBookingRepository {

    MutableLiveData<List<Booking>> currentBooking;
    MutableLiveData<List<Booking>> upComingBooking;
    MutableLiveData<List<Booking>> completedBooking;
    MutableLiveData<List<Booking>> cancelledBooking;
    MutableLiveData<String> error;
    MutableLiveData<String> currentError;
    MutableLiveData<String> detailError;
    MutableLiveData<String> upcomingError;
    MutableLiveData<String> completedError;
    MutableLiveData<String> cancelledError;
    MutableLiveData<String> rejectBookingError;
    MutableLiveData<String> status;
    TaxiBookingsAPI taxiBookingsAPI;
    TaxiBookingsAPI currentBookingAPI;
    TaxiBookingsAPI completedBookingAPI;
    TaxiBookingsAPI upcomingBookingAPI;
    TaxiBookingsAPI cancelledBookingAPI;
    MutableLiveData<Boolean> hasMoreCurrentBookings;
    MutableLiveData<Boolean> hasMoreUpcomingBookings;
    MutableLiveData<Boolean> hasMoreCompletedBookings;
    MutableLiveData<Boolean> hasMoreCancelledBookings;
    MutableLiveData<String> cancelledStatus;
    MutableLiveData<String> completedStatus;
    MutableLiveData<TaxiBookingDetailsApiResponse> taxiBookingDetails;
    MutableLiveData<RejectBookingApiResponse> rejectBookingApiResponse;


    public TaxiBookingRepository(Application application) {
        currentBooking=new MutableLiveData<>();
        upComingBooking =new MutableLiveData<>();
        completedBooking=new MutableLiveData<>();
        cancelledBooking=new MutableLiveData<>();
        hasMoreCurrentBookings=new MutableLiveData<>();
        hasMoreUpcomingBookings=new MutableLiveData<>();
        hasMoreCompletedBookings=new MutableLiveData<>();
        hasMoreCancelledBookings=new MutableLiveData<>();
        rejectBookingApiResponse=new MutableLiveData<>();
        hasMoreCurrentBookings.setValue(true);
        hasMoreUpcomingBookings.setValue(true);
        hasMoreCompletedBookings.setValue(true);
        hasMoreCancelledBookings.setValue(true);
        error=new MutableLiveData<>();
        upcomingError=new MutableLiveData<>();
        completedError=new MutableLiveData<>();
        cancelledError=new MutableLiveData<>();
        currentError=new MutableLiveData<>();
        detailError=new MutableLiveData<>();
        rejectBookingError=new MutableLiveData<>();
        status=new MutableLiveData<>();
        cancelledStatus=new MutableLiveData<>();
        completedStatus=new MutableLiveData<>();
        taxiBookingDetails=new MutableLiveData<>();
        taxiBookingsAPI= ConfigRetrofit.configRetrofit(TaxiBookingsAPI.class);
        currentBookingAPI= ConfigRetrofit.configRetrofit(TaxiBookingsAPI.class);
        upcomingBookingAPI= ConfigRetrofit.configRetrofit(TaxiBookingsAPI.class);
        completedBookingAPI= ConfigRetrofit.configRetrofit(TaxiBookingsAPI.class);
        cancelledBookingAPI= ConfigRetrofit.configRetrofit(TaxiBookingsAPI.class);
    }





    public MutableLiveData<String> getCurrentError() {
        return currentError;
    }
    public MutableLiveData<String> getdetailError() {
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

    /*public LiveData<List<Booking>> getCompletedBooking() {
        return completedBooking;
    }
    */

    public LiveData<List<Booking>> getCancelledBooking() {

        return cancelledBooking;
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

    public MutableLiveData<Boolean> getHasMoreCurrentBookings() {
        return hasMoreCurrentBookings;
    }

    public MutableLiveData<Boolean> getHasMoreUpcomingBookings() {
        return hasMoreUpcomingBookings;
    }

    public MutableLiveData<Boolean> getHasMoreCancelledBookings() {
        return hasMoreCancelledBookings;
    }

    public MutableLiveData<RejectBookingApiResponse> getRejectBookingApiResponse() {
        return rejectBookingApiResponse;
    }

    public MutableLiveData<String> getRejectBookingError() {
        return rejectBookingError;
    }

    public MutableLiveData<String> getCancelledStatus() {
        return cancelledStatus;
    }

    public MutableLiveData<String> getCompletedStatus() {
        return completedStatus;
    }

    public LiveData<List<Booking>> getCompletedBooking(String accessToken, String pageNo){
        completedBookingAPI.getAllTaxiBookings(accessToken,"completed",pageNo).enqueue(new Callback<TaxiBookingApiResponse>() {
            @Override
            public void onResponse(Call<TaxiBookingApiResponse> call, Response<TaxiBookingApiResponse> response) {
                if (response.isSuccessful() ){
                   if (response.body().success.equals("1") && response.body().response!=null){
                       completedStatus.setValue("1");
                       if (response.body().getResponse().getHasArchivedBookings().equals("0")){
                           hasMoreCompletedBookings.setValue(false);
                       }
                       else if (response.body().getResponse().getHasArchivedBookings().equals("1")){
                           hasMoreCompletedBookings.setValue(true);
                       }
                        List<Booking> bookings=response.body().getResponse().getBookings();
                        List<Booking> completedBookings=new ArrayList<Booking>();
                       for (Booking booking: bookings){
                          if (booking.getBookingType().equals("archived")){
                              completedBookings.add(booking);
                          }
                       }
                       System.out.println("Total Bookings "+completedBookings.size());
                       if (completedBookings.size()>0) {
                           completedBooking.setValue(completedBookings);
                       }else {
                           completedError.setValue("Connection Error");
                       }
                }
                else {
                       completedError.setValue(response.body().getError());
                   }
                }else {
                    completedError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<TaxiBookingApiResponse> call, Throwable t) {
                completedError.setValue("Connection Error");
            }
        });
        return completedBooking;
    }

    public LiveData<List<Booking>> getCancelledBooking(String accessToken, String pageNo){
        cancelledBookingAPI.getAllTaxiBookings(accessToken,"cancelled",pageNo).enqueue(new Callback<TaxiBookingApiResponse>() {
            @Override
            public void onResponse(Call<TaxiBookingApiResponse> call, Response<TaxiBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        cancelledStatus.setValue("1");
                        if (response.body().getResponse().getHasCancelledBookings().equals("0")){
                            hasMoreCancelledBookings.setValue(false);
                        } else if (response.body().getResponse().getHasCancelledBookings().equals("1")){
                            hasMoreCancelledBookings.setValue(true);
                        }
                        List<Booking> bookings=response.body().getResponse().getBookings();
                        List<Booking> cancelledBookings=new ArrayList<Booking>();
                        for (Booking booking: bookings){
                            if (booking.getBookingType().equals("rejected")){
                                cancelledBookings.add(booking);
                            }
                        }
                        System.out.println("Total Bookings "+cancelledBookings.size());
                        if (cancelledBookings.size()>0) {
                            cancelledBooking.setValue(cancelledBookings);
                        }else {
                            cancelledError.setValue("Connection Error");
                        }
                    }
                    else {
                        cancelledError.setValue(response.body().getError());
                    }
                }else {
                    cancelledError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<TaxiBookingApiResponse> call, Throwable t) {
                cancelledError.setValue("Connection Error");
            }
        });
        return cancelledBooking;
    }


    public LiveData<List<Booking>> getUpcomingBooking(String accessToken, String pageNo){
        Log.d("HasMoreUpcoming",hasMoreUpcomingBookings.getValue()+"");

        upcomingBookingAPI.getAllTaxiBookings(accessToken,"upcoming",pageNo).enqueue(new Callback<TaxiBookingApiResponse>() {
            @Override
            public void onResponse(Call<TaxiBookingApiResponse> call, Response<TaxiBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        if (response.body().getResponse().getHasUpcomingBookings().equals("0")){
                            hasMoreUpcomingBookings.setValue(false);
                        }else if (response.body().getResponse().getHasUpcomingBookings().equals("1")){
                            hasMoreUpcomingBookings.setValue(true);
                        }
                        List<Booking> bookings=response.body().getResponse().getBookings();
                        List<Booking> upcomingBookings=new ArrayList<Booking>();
                        for (Booking booking: bookings){
                            if (booking.getBookingType().equals("upcoming")){
                                upcomingBookings.add(booking);
                            }
                        }
                        System.out.println("Total Bookings "+upcomingBookings.size());
                        if (upcomingBookings.size()>0) {
                            upComingBooking.setValue(upcomingBookings);
                        }else {
                            upcomingError.setValue("Empty List");
                        }
                    }
                    else {
                        upcomingError.setValue(response.body().getError());
                    }
                }else {
                    upcomingError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<TaxiBookingApiResponse> call, Throwable t) {
                upcomingError.setValue("Connection Error");
            }
        });
        return upComingBooking;
    }

    public LiveData<List<Booking>> getCurrentBooking(String accessToken, String pageNo){

        taxiBookingsAPI.getAllTaxiBookings(accessToken,"current",pageNo).enqueue(new Callback<TaxiBookingApiResponse>() {
            @Override
            public void onResponse(Call<TaxiBookingApiResponse> call, Response<TaxiBookingApiResponse> response) {
                if (response.isSuccessful() ){
                    if (response.body().success.equals("1") && response.body().response!=null){
                        if (response.body().getResponse().getHasCurrentBookings().equals("0")){
                            hasMoreCurrentBookings.setValue(false);
                        }else if (response.body().getResponse().getHasCurrentBookings().equals("1")){
                            hasMoreCurrentBookings.setValue(true);
                        }
                        List<Booking> bookings=response.body().getResponse().getBookings();
                        List<Booking> currentBookings=new ArrayList<Booking>();
                        for (Booking booking: bookings){
                            if (booking.getBookingType().equals("current")){
                                currentBookings.add(booking);
                            }
                        }
                        System.out.println("Total Bookings "+currentBookings);
                        if (currentBookings.size()>0) {
                            currentBooking.setValue(currentBookings);
                        }
                        else {
                            currentError.setValue("Empty List");
                        }
                    }
                    else {
                        currentError.setValue(response.body().getError());
                    }
                }else {
                    currentError.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<TaxiBookingApiResponse> call, Throwable t) {
                currentError.setValue("Connection Error");
            }
        });
        return currentBooking;
    }



    public void getTaxiBookingDetails(String accessToken, String bookingId){
        taxiBookingsAPI.getTaxiBookingDetails(accessToken,bookingId).enqueue(new Callback<TaxiBookingDetailsApiResponse>() {
            @Override
            public void onResponse(Call<TaxiBookingDetailsApiResponse> call, Response<TaxiBookingDetailsApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1") && response.body().getResponse().getBookingDetail()!=null){
                        Log.d("BookingDetails","SetValue");
                        taxiBookingDetails.setValue(response.body());
                    }else
                        detailError.setValue(response.body().error);


                }else
                    detailError.setValue(response.body().error);
            }

            @Override
            public void onFailure(Call<TaxiBookingDetailsApiResponse> call, Throwable t) {
                detailError.setValue("Connection error");
            }
        });
    }



    public LiveData<TaxiBookingDetailsApiResponse> getTaxiBookingDetails() {
        return taxiBookingDetails;
    }


    public void rejectBooking(String accessToken, String bookingId){
        taxiBookingsAPI.rejectTaxiBooking(accessToken,bookingId).enqueue(new Callback<RejectBookingApiResponse>() {
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
