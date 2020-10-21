package com.cotrav.testalarm.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cotrav.testalarm.repos.FetchPhoneRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class AllPhoneNoViewModel extends AndroidViewModel {

    private LiveData<List<String>> phoneNoList;
    private LiveData<String> string;
    private LiveData<String> uploadCall;
    private FetchPhoneRepository fetchPhoneRepository;


    public AllPhoneNoViewModel(Application application) {
        super(application);

        fetchPhoneRepository = new FetchPhoneRepository(this.getApplication());
        uploadCall = fetchPhoneRepository.getUploadLead();
    }

    public LiveData<String> getUploadCall() {
        uploadCall = fetchPhoneRepository.getUploadLead();
        return uploadCall;
    }

    public LiveData<String> getUploadCall(JsonArray jsonArray) {

        uploadCall = fetchPhoneRepository.getUploadLead(jsonArray);
        return uploadCall;
    }

    public void setUploadCall(LiveData<String> uploadCall) {
        this.uploadCall = uploadCall;
    }

    public LiveData<String> getPhoneNoList(JsonObject jsonObject) {
        string = fetchPhoneRepository.getPhoneNoList(jsonObject);
        return string;
    }
}
