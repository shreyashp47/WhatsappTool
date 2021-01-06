package com.taxivaxi.operator.repository;

import androidx.lifecycle.MutableLiveData;

import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.model.archivedbooking.BookingApiResponse;
import com.taxivaxi.operator.retrofit.ConfigRetrofit;
import com.taxivaxi.operator.retrofit.DutySlipAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DutySlipRepository {

    MutableLiveData<List<Booking>> dutySlipList;
    MutableLiveData<String> error;
    DutySlipAPI dutySlipAPI;

    public DutySlipRepository() {
        dutySlipList = new MutableLiveData<>();
        error = new MutableLiveData<>();
        dutySlipAPI = ConfigRetrofit.configRetrofit(DutySlipAPI.class);
    }

    public MutableLiveData<List<Booking>> getDutySlipList() {
        return dutySlipList;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void getDutySlipList(String accessToken) {
        dutySlipAPI.getPendingDutySlip(accessToken).enqueue(new Callback<BookingApiResponse>() {
            @Override
            public void onResponse(Call<BookingApiResponse> call, Response<BookingApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1") && response.body().getResponse() != null) {
                        dutySlipList.setValue(response.body().getResponse().getBookings());
                    } else {
                        error.setValue(response.body().getError());
                    }
                } else {
                    error.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<BookingApiResponse> call, Throwable t) {
                error.setValue("Connection Error: " + t.getMessage());
            }
        });
    }
}
