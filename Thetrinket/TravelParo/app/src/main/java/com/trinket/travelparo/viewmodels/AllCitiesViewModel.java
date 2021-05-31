package com.taxivaxi.spoc.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.allcitiesmodel.City;
import com.taxivaxi.spoc.repository.AllCitiesRepository;

import java.util.List;



public class AllCitiesViewModel extends AndroidViewModel {

    private LiveData<List<City>>  citiesLiveData;
    private LiveData<List<City>>  allcitiesLiveData;
    private LiveData<List<City>>  hotelCitiesLiveData;
    private AllCitiesRepository allCitiesRepository;

    public AllCitiesViewModel(Application application) {
        super(application);
        allCitiesRepository=new AllCitiesRepository(this.getApplication());
    }

    public void initAllCitiesViewModel(String access_token,String admin_id){
        if (citiesLiveData==null) {
            citiesLiveData = allCitiesRepository.getCities(access_token, admin_id);
        }
    }

    public LiveData<List<City>> getCitiesLiveData(String access_token,String admin_id) {
        if (citiesLiveData==null) {
            citiesLiveData=allCitiesRepository.getCities(access_token,admin_id);
        }
        return citiesLiveData;
    }   public LiveData<List<City>> getAllCitiesLiveData(String access_token,String admin_id) {

            allcitiesLiveData=allCitiesRepository.getAllCities(access_token,admin_id);

        return allcitiesLiveData;
    }

    public LiveData<List<City>> getHotelCitiesLiveData(String access_token,String admin_id) {
        if (hotelCitiesLiveData==null) {
            hotelCitiesLiveData=allCitiesRepository.getHotelCitiesLiveData(access_token,admin_id);
        }
        return hotelCitiesLiveData;
    }
}
