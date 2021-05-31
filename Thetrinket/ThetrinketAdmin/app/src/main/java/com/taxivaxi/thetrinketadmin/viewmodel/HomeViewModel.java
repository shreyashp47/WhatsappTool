package com.taxivaxi.thetrinketadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.thetrinketadmin.model.home.CategoriesApiResponse;
import com.taxivaxi.thetrinketadmin.repository.HomeRepository;

import java.util.List;


public class HomeViewModel extends AndroidViewModel {

    HomeRepository homeRepository;
    LiveData<List<CategoriesApiResponse>> listLiveData;
    LiveData<String> error;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository =new HomeRepository(getApplication());
        listLiveData = homeRepository.getCategoryList();

    }

    public LiveData<List<CategoriesApiResponse>> getListLiveData(String token) {
        return listLiveData=homeRepository.getCategoryList(token);
    }

    public LiveData<String> getError() {
        return error;
    }


}
