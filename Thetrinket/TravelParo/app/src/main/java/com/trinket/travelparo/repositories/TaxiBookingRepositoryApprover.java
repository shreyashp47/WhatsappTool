package com.taxivaxi.approver.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.taxivaxi.approver.models.BookingDetailsmodel.BookingDetailRespoce;
import com.taxivaxi.approver.models.BookingDetailsmodel.Person;
import com.taxivaxi.approver.models.acceptrejectbookingmodel.AcceptRejectBookingApiRespone;
import com.taxivaxi.approver.models.bookingmodel.BookingsApiResponse;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.retrofit.ConfigRetrofit;
import com.taxivaxi.approver.retrofit.TaxiBookingAPI;
import com.taxivaxi.approver.room.AppDatabase;
import com.taxivaxi.approver.room.dao.TaxiBookingDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaxiBookingRepository {
    MutableLiveData<List<TaxiBooking>> allTaxiBookings;

    MutableLiveData<List<Person>> listLiveDataPerson;
    TaxiBookingAPI taxiBookingAPI;
    AppDatabase appDatabase;
    TaxiBookingDAO taxiBookingDAO;
    MutableLiveData<String> approveBookingStatus;
    MutableLiveData<String> rejectBookingStatus;

    MutableLiveData<List<TaxiBooking>> approvedBookings;
    MutableLiveData<List<TaxiBooking>> archivedBookings;
    MutableLiveData<List<TaxiBooking>> rejectedBookings;
    MutableLiveData<List<TaxiBooking>> unAssignedBookings;


    MutableLiveData<String> newError, approvedError, archivedError, cancelledError;
    MutableLiveData<Boolean> hasMoreNewBookings;
    MutableLiveData<Boolean> hasMoreApprovedBookings;
    MutableLiveData<Boolean> hasMoreArchivedBookings;
    MutableLiveData<Boolean> hasMoreCancelledBookings;

    public TaxiBookingRepository(Application application) {
        appDatabase = Room.databaseBuilder(application,
                AppDatabase.class, "Taxi_Booking_Database")
                .allowMainThreadQueries()
                .build();
        taxiBookingDAO = appDatabase.taxiBookingDAO();
        allTaxiBookings = new MutableLiveData<>();/*
        approvedBookings = taxiBookingDAO.getApprovedBookings();
        archivedBookings = taxiBookingDAO.getArchivedBookings();
        rejectedBookings = taxiBookingDAO.getRejectedBookings();
        unAssignedBookings = taxiBookingDAO.getUnApprovedBookings();*/

        taxiBookingAPI = ConfigRetrofit.configRetrofit(TaxiBookingAPI.class);
        approveBookingStatus = new MutableLiveData<>();
        listLiveDataPerson = new MutableLiveData<>();
        rejectBookingStatus = new MutableLiveData<>();

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
        hasMoreCancelledBookings = new MutableLiveData<>();
    }

    public MutableLiveData<String> getNewError() {
        return newError;
    }

    public void setNewError(MutableLiveData<String> newError) {
        this.newError = newError;
    }

    public MutableLiveData<String> getApprovedError() {
        return approvedError;
    }

    public void setApprovedError(MutableLiveData<String> approvedError) {
        this.approvedError = approvedError;
    }

    public MutableLiveData<String> getArchivedError() {
        return archivedError;
    }

    public void setArchivedError(MutableLiveData<String> archivedError) {
        this.archivedError = archivedError;
    }

    public MutableLiveData<String> getCancelledError() {
        return cancelledError;
    }

    public void setCancelledError(MutableLiveData<String> cancelledError) {
        this.cancelledError = cancelledError;
    }

    public LiveData<List<TaxiBooking>> getApprovedBookings() {
        return approvedBookings;
    }

    public LiveData<List<TaxiBooking>> getArchivedBookings() {
        return archivedBookings;
    }

    public LiveData<List<TaxiBooking>> getRejectedBookings() {
        return rejectedBookings;
    }

    public LiveData<List<TaxiBooking>> getUnAssignedBookings() {
        return unAssignedBookings;
    }

    public LiveData<String> getApproveBookingStatus() {
        return approveBookingStatus;
    }

    public LiveData<List<Person>> getTaxiDetails() {
        return listLiveDataPerson;
    }

    public LiveData<String> getRejectBookingStatus() {
        return rejectBookingStatus;
    }

    public void getAllBookings(String accessToken, String authType, final String booking_type, String page_no) {

        if (authType.equals("Approver 1")) {
            taxiBookingAPI.getAuthOneTaxiBookings(accessToken, booking_type, page_no).enqueue(new Callback<BookingsApiResponse>() {
                @Override
                public void onResponse(Call<BookingsApiResponse> call, Response<BookingsApiResponse> response) {
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
                public void onFailure(Call<BookingsApiResponse> call, Throwable t) {
                    setError(booking_type, "Connection Error " + t.getMessage());
                }
            });
        } else if (authType.equals("Approver 2")) {
            taxiBookingAPI.getAuthTwoTaxiBookings(accessToken, booking_type).enqueue(new Callback<BookingsApiResponse>() {
                @Override
                public void onResponse(Call<BookingsApiResponse> call, Response<BookingsApiResponse> response) {
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
                public void onFailure(Call<BookingsApiResponse> call, Throwable t) {
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

    public void getTaixDetails(String accessToken, String bookingId) {
        taxiBookingAPI.taxiDetals(accessToken, bookingId).enqueue(new Callback<BookingDetailRespoce>() {
            @Override
            public void onResponse(Call<BookingDetailRespoce> call, Response<BookingDetailRespoce> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1") &&
                            response.body().getResponse().getBookingDetail().getPeople().size() > 0) {
                        listLiveDataPerson.setValue(response.body().getResponse().getBookingDetail().getPeople());
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingDetailRespoce> call, Throwable t) {

            }
        });
    }

    public void approveTaxiBooking(String accessToken, String authType, String bookingId) {
        if (authType.equals("Approver 1")) {
            taxiBookingAPI.approveAuthOneTaxiBooking(accessToken, bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            approveBookingStatus.setValue(response.body().response.message);
                        } else {
                            approveBookingStatus.setValue(response.body().error);
                        }
                    } else {
                        approveBookingStatus.setValue("Connection Error");
                    }

                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    approveBookingStatus.setValue("Connection Error " + t.getMessage());
                }
            });
        } else if (authType.equals("Approver 2")) {
            taxiBookingAPI.approveAuthTwoTaxiBooking(accessToken, bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            approveBookingStatus.setValue(response.body().response.message);
                        } else {
                            approveBookingStatus.setValue(response.body().error);
                        }
                    } else {
                        approveBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    approveBookingStatus.setValue("Connection Error " + t.getMessage());
                }
            });
        }
    }

    public void rejectTaxiBooking(String accessToken, String authType, String bookingId) {
        if (authType.equals("Approver 1")) {
            taxiBookingAPI.rejectAuthOneTaxiBooking(accessToken, bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            rejectBookingStatus.setValue(response.body().response.message);
                        } else {
                            rejectBookingStatus.setValue(response.body().error);
                        }
                    } else {
                        rejectBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    rejectBookingStatus.setValue("Connection Error " + t.getMessage());
                }
            });
        } else if (authType.equals("Approver 2")) {
            taxiBookingAPI.rejectAuthTwoTaxiBooking(accessToken, bookingId).enqueue(new Callback<AcceptRejectBookingApiRespone>() {
                @Override
                public void onResponse(Call<AcceptRejectBookingApiRespone> call, Response<AcceptRejectBookingApiRespone> response) {
                    if (response.isSuccessful()) {
                        if (response.body().success.equals("1")) {
                            rejectBookingStatus.setValue(response.body().response.message);
                        } else {
                            rejectBookingStatus.setValue(response.body().error);
                        }
                    } else {
                        rejectBookingStatus.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AcceptRejectBookingApiRespone> call, Throwable t) {
                    rejectBookingStatus.setValue("Connection Error " + t.getMessage());
                }
            });
        }
    }

    void deleteDatabase() {
        taxiBookingDAO.deleteTaxiBookings();
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
}
