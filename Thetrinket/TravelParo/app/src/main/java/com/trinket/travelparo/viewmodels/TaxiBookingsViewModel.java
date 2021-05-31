package com.taxivaxi.spoc.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import com.taxivaxi.spoc.model.taxibookingmodel.Booking;
import com.taxivaxi.spoc.model.taxidetailsmodels.TaxiBookingDetailsApiResponse;
import com.taxivaxi.spoc.repository.TaxiBookingRepository;

import java.util.List;

/**
 * Created by sandeep on 25/9/17.
 */

public class TaxiBookingsViewModel extends AndroidViewModel {
    TaxiBookingRepository taxiBookingRepository;
    LiveData<List<Booking>> currentBookings;
    LiveData<List<Booking>> upcomingBooking;
    LiveData<List<Booking>> completeBooking;
    LiveData<List<Booking>> cancelledBooking;
    LiveData<String>  error;
    LiveData<String> currentError;
    LiveData<String> detailError;
    LiveData<String> upcomingError;
    LiveData<String> completedError;
    LiveData<String> cancelledError;
    LiveData<Boolean> hasMoreCompletedBookings;
    LiveData<Boolean> hasMoreCancelledBooking;
    LiveData<Boolean> hasMoreUpcomingBooking;
    LiveData<Boolean> hasMoreCurrentBooking;
    LiveData<TaxiBookingDetailsApiResponse> taxiBookingDetailsApiResponse;
    LiveData<RejectBookingApiResponse> rejectBookingApiResponse;
    LiveData<String> rejectBookingError;



    public TaxiBookingsViewModel(Application application) {
        super(application);
        taxiBookingRepository=new TaxiBookingRepository(application);
        error=taxiBookingRepository.getError();
        hasMoreCompletedBookings=taxiBookingRepository.getHasMoreCompletedBookings();
        hasMoreCurrentBooking=taxiBookingRepository.getHasMoreCurrentBookings();
        hasMoreUpcomingBooking=taxiBookingRepository.getHasMoreUpcomingBookings();
        hasMoreCancelledBooking=taxiBookingRepository.getHasMoreCancelledBookings();
        currentError=taxiBookingRepository.getCurrentError();
        detailError=taxiBookingRepository.getdetailError();
        upcomingError=taxiBookingRepository.getUpcomingError();
        completedError=taxiBookingRepository.getCompletedError();
        cancelledError=taxiBookingRepository.getCancelledError();
        taxiBookingDetailsApiResponse=taxiBookingRepository.getTaxiBookingDetails();
        rejectBookingApiResponse=taxiBookingRepository.getRejectBookingApiResponse();
        rejectBookingError =taxiBookingRepository.getRejectBookingError();
    }

    public LiveData<String> getRejectBookingError() {
        return rejectBookingError;
    }

    public LiveData<RejectBookingApiResponse> getRejectBookingApiResponse() {
        return rejectBookingApiResponse;
    }

    public void rejectBooking(String accessToken,String bookingId){
        taxiBookingRepository.rejectBooking(accessToken,bookingId);
    }

    public LiveData<List<Booking>> getCurrentBookings(String accessToken, String pageNo) {
        currentBookings=taxiBookingRepository.getCurrentBooking(accessToken,pageNo);
        return currentBookings;
    }

    public LiveData<String> getUpcomingError() {
        return upcomingError;
    }

    public LiveData<String> getCompletedError() {
        return completedError;
    }

    public LiveData<String> getCancelledError() {
        return cancelledError;
    }

    public LiveData<List<Booking>> getUpcomingBooking(String accessToken, String pageNo){
        upcomingBooking=taxiBookingRepository.getUpcomingBooking(accessToken,pageNo);
        return upcomingBooking;
    }

    public LiveData<List<Booking>> getCompleteBooking(String accessToken,String pageNo) {
        completeBooking=taxiBookingRepository.getCompletedBooking(accessToken,pageNo);
        return completeBooking;
    }

    public LiveData<List<Booking>> getCancelledBooking(String accessToken,String pageNo) {
        cancelledBooking=taxiBookingRepository.getCancelledBooking(accessToken,pageNo);
        return cancelledBooking;
    }

    public LiveData<Boolean> getHasMoreCancelledBooking() {
        return hasMoreCancelledBooking;
    }

    public LiveData<String> getCurrentError() {
        return currentError;
    }
    public LiveData<String> detailError() {
        return detailError;
    }

    public LiveData<Boolean> getHasMoreUpcomingBooking() {
        return hasMoreUpcomingBooking;
    }

    public LiveData<Boolean> getHasMoreCurrentBooking() {
        return hasMoreCurrentBooking;
    }

    public LiveData<List<Booking>> getCancelledBooking() {
        return cancelledBooking;
    }

    public LiveData<String> getError() {
        return error;
    }
    public void refresh(String accessToken,String pageNo,String type){
        switch (type){
            case "Completed": taxiBookingRepository.getCompletedBooking(accessToken,pageNo);
                break;
            case "Cancelled": taxiBookingRepository.getCancelledBooking(accessToken,pageNo);
                break;
            case "Upcoming": taxiBookingRepository.getUpcomingBooking(accessToken,pageNo);
                break;
            case "Current": taxiBookingRepository.getCurrentBooking(accessToken,pageNo);
                break;
            default: break;
        }

    }

    public LiveData<Boolean> getHasMoreCompletedBookings() {
        return hasMoreCompletedBookings;
    }

    public void getTaxiBookingDetails(String accessToken, String bookingId){
        taxiBookingRepository.getTaxiBookingDetails(accessToken,bookingId);
    }

    public LiveData<TaxiBookingDetailsApiResponse> getTaxiBookingDetailsApiResponse(){
        return taxiBookingDetailsApiResponse;
    }
}
