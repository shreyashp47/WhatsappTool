package com.taxivaxi.operator.viewmode;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.repository.DutySlipRepository;

import java.util.List;


public class DutySlipViewModel extends AndroidViewModel {

    DutySlipRepository dutySlipRepository;
    LiveData<List<Booking>> dutySlipList;
    LiveData<String> error;

    public DutySlipViewModel(@NonNull Application application) {
        super(application);
        dutySlipRepository =new DutySlipRepository();
        dutySlipList = dutySlipRepository.getDutySlipList();
        error= dutySlipRepository.getError();
    }

    public LiveData<List<Booking>> getDutySlipList() {
        return dutySlipList;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void getDutySlips(String accessToken){
        dutySlipRepository.getDutySlipList(accessToken);
    }
}
