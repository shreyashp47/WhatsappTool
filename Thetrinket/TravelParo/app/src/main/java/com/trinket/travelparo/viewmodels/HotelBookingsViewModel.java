package com.trinket.travelparo.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.hotelbookingdetaismodel.HotelBookingDetailsApiResponse;
import com.taxivaxi.spoc.model.hotelbookingmodel.Booking;
import com.taxivaxi.spoc.model.rejectbookingmodel.RejectBookingApiResponse;
import com.taxivaxi.spoc.repository.HotelBookingRepository;

import java.util.List;

/**
 * Created by sandeep on 3/10/17.
 */

public class HotelBookingsViewModel extends AndroidViewModel {

    HotelBookingRepository hotelBookingRepository;
    LiveData<List<Booking>> currentBookings;
    LiveData<List<Booking>> upcomingBooking;
    LiveData<List<Booking>> completeBooking;
    LiveData<List<Booking>> cancelledBooking;
    LiveData<String> error;
    LiveData<String> currentError;
    LiveData<String> detailError;
    LiveData<String> upcomingError;
    LiveData<String> completedError;
    LiveData<String> cancelledError;
    LiveData<Boolean> hasMoreCompletedBookings;
    LiveData<Boolean> hasMoreCancelledBooking;
    LiveData<Boolean> hasMoreUpcomingBooking;
    LiveData<Boolean> hasMoreCurrentBooking;
    LiveData<HotelBookingDetailsApiResponse> hotelBookingDetailsApiResponse;
    LiveData<RejectBookingApiResponse> rejectBookingApiResponse;
    LiveData<String> rejectBookingError;

    public HotelBookingsViewModel(Application application) {
        super(application);
        hotelBookingRepository =new HotelBookingRepository(application);
        error= hotelBookingRepository.getError();
        hasMoreCompletedBookings= hotelBookingRepository.getHasMoreCompletedBookings();
        hasMoreCurrentBooking= hotelBookingRepository.getHasMoreCurrentBookings();
        hasMoreUpcomingBooking= hotelBookingRepository.getHasMoreUpcomingBookings();
        hasMoreCancelledBooking= hotelBookingRepository.getHasMoreCancelledBookings();
        currentError= hotelBookingRepository.getCurrentError();
        detailError= hotelBookingRepository.detailError();
        upcomingError= hotelBookingRepository.getUpcomingError();
        completedError= hotelBookingRepository.getCompletedError();
        cancelledError= hotelBookingRepository.getCancelledError();
        hotelBookingDetailsApiResponse=hotelBookingRepository.getHotelBookingDetailsApiResponse();
        rejectBookingApiResponse=hotelBookingRepository.getRejectBookingApiResponse();
        rejectBookingError=hotelBookingRepository.getRejectBookingError();
    }

    public LiveData<RejectBookingApiResponse> getRejectBookingApiResponse() {
        return rejectBookingApiResponse;
    }

    public LiveData<String> getRejectBookingError() {
        return rejectBookingError;
    }

    public LiveData<List<Booking>> getCurrentBookings(String accessToken, String pageNo) {
        currentBookings= hotelBookingRepository.getCurrentBooking(accessToken,pageNo);
        return currentBookings;
    }

    public LiveData<List<Booking>> getUpcomingBooking(String accessToken, String pageNo){
        upcomingBooking= hotelBookingRepository.getUpcomingBooking(accessToken,pageNo);
        return upcomingBooking;
    }

    public LiveData<List<Booking>> getCompleteBooking(String accessToken, String pageNo) {
        completeBooking= hotelBookingRepository.getCompletedBooking(accessToken,pageNo);
        return completeBooking;
    }

    public LiveData<List<Booking>> getCancelledBooking(String accessToken, String pageNo) {
        cancelledBooking= hotelBookingRepository.getCancelledBooking(accessToken,pageNo);
        return cancelledBooking;
    }

    public LiveData<HotelBookingDetailsApiResponse> getHotelBookingDetailsApiResponse() {
        return hotelBookingDetailsApiResponse;
    }

    public void getHotelBookingDetails(String accessToken, String bookingId){
        hotelBookingRepository.getHotelBookingDetails(accessToken,bookingId);
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
    public void refresh(String accessToken, String pageNo, String type){
        switch (type){
            case "Completed": hotelBookingRepository.getCompletedBooking(accessToken,pageNo);
                break;
            case "Cancelled": hotelBookingRepository.getCancelledBooking(accessToken,pageNo);
                break;
            case "Upcoming": hotelBookingRepository.getUpcomingBooking(accessToken,pageNo);
                break;
            case "Current": hotelBookingRepository.getCurrentBooking(accessToken,pageNo);
                break;
            default: break;
        }

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

    public LiveData<Boolean> getHasMoreCompletedBookings() {
        return hasMoreCompletedBookings;
    }

    public void rejectHotelBooking(String accessToken, String bookingId){
        hotelBookingRepository.rejectHotelBooking(accessToken,bookingId);
    }

}
