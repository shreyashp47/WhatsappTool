package com.taxivaxi.approver.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.taxivaxi.approver.models.BookingDetailsmodel.BookingDetailRespoce;
import com.taxivaxi.approver.models.BookingDetailsmodel.Person;
import com.taxivaxi.approver.models.acceptrejectbookingmodel.AcceptRejectBookingApiRespone;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBookingApiResponse;
import com.taxivaxi.approver.retrofit.ConfigRetrofit;
import com.taxivaxi.approver.retrofit.HotelBookingsAPI;
import com.taxivaxi.approver.room.AppDatabase;
import com.taxivaxi.approver.room.dao.HotelBookingDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelBookingRepository {
     List<HotelBooking> approvedBookingsdb;

    HotelBookingsAPI hotelBookingsAPI;
    MutableLiveData<List<Person>> listLiveDataPerson;
    AppDatabase appDatabase;
    HotelBookingDAO hotelBookingDAO;
    MutableLiveData<String> approveBookingStatus;
    MutableLiveData<String> rejectBookingStatus;
    MutableLiveData<List<HotelBooking>> approvedBookings;
    MutableLiveData<List<HotelBooking>> archivedBookings;
    MutableLiveData<List<HotelBooking>> rejectedBookings;
    MutableLiveData<List<HotelBooking>> unAssignedBookings;
    MutableLiveData<String> newError, approvedError, archivedError, cancelledError;
    MutableLiveData<Boolean> hasMoreNewBookings;
    MutableLiveData<Boolean> hasMoreApprovedBookings;
    MutableLiveData<Boolean> hasMoreArchivedBookings;
    MutableLiveData<Boolean> hasMoreCancelledBookings;
    public HotelBookingRepository(Application application){
        appDatabase= Room.databaseBuilder(application,
                AppDatabase.class, "Bus_Booking_Database")
                .allowMainThreadQueries()
                .build();
        hotelBookingDAO =appDatabase.hotelBookingDAO();


        hotelBookingsAPI = ConfigRetrofit.configRetrofit(HotelBookingsAPI.class);
        approveBookingStatus=new MutableLiveData<>();
        rejectBookingStatus=new MutableLiveData<>();
        listLiveDataPerson=new MutableLiveData<>();
        approvedBookings = new MutableLiveData<>();
        archivedBookings = new MutableLiveData<>();
        rejectedBookings = new MutableLiveData<>();
        unAssignedBookings = new MutableLiveData<>();
        newError = new MutableLiveData<>();
        approvedError = new MutableLiveData<>();
        archivedError = new MutableLiveData<>();
        cancelledError = new MutableLiveData<>();
        hasMoreNewBookings = new MutableLiveData<>();
        hasMoreApprovedBookings = new MutableLiveData<>();
        hasMoreArchivedBookings = new MutableLiveData<>();
        hasMoreCancelledBookings = new MutableLiveData<>();}


    public MutableLiveData<String> getNewError() {
        return newError;
    }

    public MutableLiveData<String> getApprovedError() {
        return approvedError;
    }

    public MutableLiveData<String> getArchivedError() {
        return archivedError;
    }

    public MutableLiveData<String> getCancelledError() {
        return cancelledError;
    }

    public MutableLiveData<Boolean> getHasMoreNewBookings() {
        return hasMoreNewBookings;
    }

    public MutableLiveData<Boolean> getHasMoreApprovedBookings() {
        return hasMoreApprovedBookings;
    }

    public MutableLiveData<Boolean> getHasMoreArchivedBookings() {
        return hasMoreArchivedBookings;
    }

    public MutableLiveData<Boolean> getHasMoreCancelledBookings() {
        return hasMoreCancelledBookings;
    }

    public LiveData<List<HotelBooking>> getApprovedBookings() {
        return approvedBookings;
    }

    public LiveData<List<HotelBooking>> getArchivedBookings() {
        return archivedBookings;
    }

    public LiveData<List<HotelBooking>> getRejectedBookings() {
        return rejectedBookings;
    }

    public LiveData<List<HotelBooking>> getUnAssignedBookings() {
        return unAssignedBookings;
    }

    public LiveData<String> getApproveBookingStatus() {
        return approveBookingStatus;
    }

    public LiveData<String> getRejectBookingStatus() {
        return rejectBookingStatus;
    }

    public LiveData<List<Person>> getTaxiDetails() {
        return listLiveDataPerson;
    }


    public void getAllBookings(String accessToken, String authType, final String booking_type, String page_no){

        if (authType.equals("Approver 1")){
            hotelBookingsAPI.getAuthOneHotelBookings(accessToken,booking_type,page_no).enqueue(new Callback<HotelBookingApiResponse>() {
                @Override
                public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            if (response.body().response.bookings != null) {

                                //   taxiBookingDAO.addTaxiBookings(response.body().response.bookings);


                                if (booking_type.equals("archived")) {
                                    archivedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreArchivedBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreArchivedBookings.setValue(true);

                                }
                                if (booking_type.equals("approved")) {
                                    approvedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreApprovedBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreApprovedBookings.setValue(true);
                                }
                                if (booking_type.equals("rejected")) {
                                    rejectedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreCancelledBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreCancelledBookings.setValue(true);
                                }
                                if (booking_type.equals("unapproved")) {
                                    unAssignedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreNewBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreNewBookings.setValue(true);
                                }

                            } else {
                                setError(booking_type, "No Bookings");
                            }
                        } else {
                            setError(booking_type, response.body().error);
                        }
                    } else {
                        setError(booking_type, "Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                    setError(booking_type, "Connection Error " + t.getMessage());
                }
            });
        }
        else if (authType.equals("Approver 2")){
            hotelBookingsAPI.getAuthTwoHotelBookings(accessToken,booking_type,page_no).enqueue(new Callback<HotelBookingApiResponse>() {
                @Override
                public void onResponse(Call<HotelBookingApiResponse> call, Response<HotelBookingApiResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            if (response.body().response.bookings != null) {

                                //   taxiBookingDAO.addTaxiBookings(response.body().response.bookings);


                                if (booking_type.equals("archived")) {
                                    archivedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreArchivedBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreArchivedBookings.setValue(true);

                                }
                                if (booking_type.equals("approved")) {
                                    approvedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreApprovedBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreApprovedBookings.setValue(true);
                                }
                                if (booking_type.equals("rejected")) {
                                    rejectedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreCancelledBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreCancelledBookings.setValue(true);
                                }
                                if (booking_type.equals("unapproved")) {
                                    unAssignedBookings.setValue(response.body().response.bookings);
                                    if (response.body().response.has_more_bookings.equals("0"))
                                        hasMoreNewBookings.setValue(false);
                                    else if (response.body().response.has_more_bookings.equals("1"))
                                        hasMoreNewBookings.setValue(true);
                                }

                            } else {
                                setError(booking_type, "No Bookings");
                            }
                        } else {
                            setError(booking_type, response.body().error);
                        }
                    } else {
                        setError(booking_type, "Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<HotelBookingApiResponse> call, Throwable t) {
                    setError(booking_type, "Connection Error " + t.getMessage());
                }
            });
        }
    }

    void setError(String booking_type, String text) {
        if (booking_type.equals("archived"))
            archivedError.setValue(text);
        if (booking_type.equals("approved"))
            approvedError.setValue(text);
        if (booking_type.equals("rejected"))
            cancelledError.setValue(text);
        if (booking_type.equals("unapproved"))
            newError.setValue(text);

    }
    public void getDetails(String accessToken, String bookingId){
        hotelBookingsAPI.Detals(accessToken,bookingId).enqueue(new Callback<BookingDetailRespoce>() {
            @Override
            public void onResponse(Call<BookingDetailRespoce> call, Response<BookingDetailRespoce> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1")&&
                            response.body().getResponse().getBookingDetail().getPeople().size()>0){
                        listLiveDataPerson.setValue(response.body().getResponse().getBookingDetail().getPeople());
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingDetailRespoce> call, Throwable t) {

            }
        });
    }


    public void approveTaxiBooking(String accessToken, String authType, String bookingId,String bookingStatus){
        if (authType.equals("Approver 1")){
            hotelBookingsAPI.approveAuthOneHotelBooking(accessToken,bookingId,bookingStatus).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()){
                        if (response.body().success.equals("1")){
                            approveBookingStatus.setValue(response.body().response.message);
                        }
                        else {
                            approveBookingStatus.setValue(response.body().error);
                        }
                    }else {
                        approveBookingStatus.setValue("Connection Error");
                    }

                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    approveBookingStatus.setValue("Connection Error "+t.getMessage());
                }
            });
        }else if (authType.equals("Approver 2")){
            hotelBookingsAPI.approveAuthTwoHotelBooking(accessToken,bookingId,bookingStatus).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()){
                        if (response.body().success.equals("1")){
                            approveBookingStatus.setValue(response.body().response.message);
                        }else {
                            approveBookingStatus.setValue(response.body().error);
                        }
                    }else {
                        approveBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    approveBookingStatus.setValue("Connection Error "+t.getMessage());
                }
            });
        }
    }

    public void rejectTaxiBooking(String accessToken, String authType, String bookingId){
        if (authType.equals("Approver 1")){
            hotelBookingsAPI.rejectAuthOneHotelBooking(accessToken,bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            rejectBookingStatus.setValue(response.body().response.message);
                        } else {
                            rejectBookingStatus.setValue(response.body().error);
                        }
                    }
                    else {
                        rejectBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    rejectBookingStatus.setValue("Connection Error "+t.getMessage());
                }
            });
        }
        else if (authType.equals("Approver 2")){
            hotelBookingsAPI.rejectAuthTwoHotelBooking(accessToken,bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()){
                        if (response.body().success.equals("1")){
                            approveBookingStatus.setValue(response.body().response.message);
                        }else {
                            approveBookingStatus.setValue(response.body().error);
                        }
                    }else {
                        approveBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    approveBookingStatus.setValue("Connection Error "+t.getMessage());
                }
            });
        }
    }

    void  deleteDatabase(){
        hotelBookingDAO.deleteHotelBookings();
    }


}
