package com.taxivaxi.spoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.taxivaxi.spoc.model.allcitiesmodel.AllCitiesApiResponse;
import com.taxivaxi.spoc.model.allcitiesmodel.CitiesResponse;
import com.taxivaxi.spoc.model.allcitiesmodel.City;
import com.taxivaxi.spoc.retrofit.AllCitiesAPI;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.utility.GsonStringConvertor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sandeep on 17/7/17.
 */

public class AllCitiesRepository {
    SharedPreferences allCitiesSharedPreferences;
    MutableLiveData<List<City>> citiesLiveData;
    MutableLiveData<List<City>> allcitiesLiveData;
    MutableLiveData<List<City>> hotelCitiesLiveData;
    AllCitiesAPI allCitiesAPI;

    Application application;

    public AllCitiesRepository(Application application) {
        this.application = application;
        allCitiesSharedPreferences = application.getSharedPreferences("cities_info", Context.MODE_PRIVATE);
        citiesLiveData = new MutableLiveData<>();
        allcitiesLiveData = new MutableLiveData<>();
        hotelCitiesLiveData = new MutableLiveData<>();
        allCitiesAPI = ConfigRetrofit.configRetrofit(AllCitiesAPI.class);


        if (allCitiesSharedPreferences.getString("cities", "n") != "n") {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("cities", "n"));
            CitiesResponse citiesResponse = GsonStringConvertor.stringToGson(allCitiesSharedPreferences.getString("cities", "n"), CitiesResponse.class);
            citiesLiveData.setValue(citiesResponse.cities);
            Log.d("dCities", allCitiesSharedPreferences.getString("cities", "n"));
        } else {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("cities", "n"));
            Log.d("dCities", allCitiesSharedPreferences.getString("cities", "n"));
        }

        if (allCitiesSharedPreferences.getString("cities_hotel", "n") != "n") {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("cities_hotel", "n"));
            CitiesResponse citiesResponse = GsonStringConvertor.stringToGson(allCitiesSharedPreferences.getString("cities_hotel", "n"), CitiesResponse.class);
            hotelCitiesLiveData.setValue(citiesResponse.cities);
            //Log.d("dCities",allCitiesSharedPreferences.getString("cities","n"));
        }
    }

    public MutableLiveData<List<City>> getHotelCitiesLiveData(String access_token, String admin_id) {
        refreshHotelCitiesData(access_token, admin_id);
        return hotelCitiesLiveData;
    }

    public LiveData<List<City>> getCities(String access_token, String admin_id) {
        if (allCitiesSharedPreferences.getString("cities", "n") != "n") {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("cities", "n"));
            CitiesResponse citiesResponse = GsonStringConvertor.stringToGson(allCitiesSharedPreferences.getString("cities", "n"), CitiesResponse.class);
            citiesLiveData.setValue(citiesResponse.cities);
        } else {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("cities", "n"));
            Log.d("dCities", allCitiesSharedPreferences.getString("cities", "n"));
        }
        refreshCitiesData(access_token, admin_id);
        return citiesLiveData;
    }

    public LiveData<List<City>> getAllCities(String access_token, String admin_id) {
        if (allCitiesSharedPreferences.getString("allcities", "n") != "n") {
            System.out.println("Cities: " + allCitiesSharedPreferences.getString("allcities", "n"));
            CitiesResponse citiesResponse = GsonStringConvertor.stringToGson(allCitiesSharedPreferences.getString("allcities", "n"), CitiesResponse.class);
            allcitiesLiveData.setValue(citiesResponse.cities);
        } else {
            System.out.println("All Cities: " + allCitiesSharedPreferences.getString("allcities", "n"));
            Log.d("All Cities", allCitiesSharedPreferences.getString("allcities", "n"));
        }
        refreshAllCitiesData(access_token, admin_id);
        return allcitiesLiveData;
    }

    void refreshCitiesData(String access_token, String admin_id) {
        allCitiesAPI.getCities(access_token, admin_id).enqueue(new Callback<AllCitiesApiResponse>() {
            @Override
            public void onResponse(Call<AllCitiesApiResponse> call, Response<AllCitiesApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response != null) {
                        SharedPreferences.Editor editor = allCitiesSharedPreferences.edit();
                        editor.putString("cities", GsonStringConvertor.gsonToString(response.body().response));
                        citiesLiveData.setValue(response.body().response.cities);
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllCitiesApiResponse> call, Throwable t) {

            }
        });
    }
    void refreshAllCitiesData(String access_token, String admin_id) {
        allCitiesAPI.getAllCities(access_token, "Taxivaxi").enqueue(new Callback<AllCitiesApiResponse>() {
            @Override
            public void onResponse(Call<AllCitiesApiResponse> call, Response<AllCitiesApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response != null) {
                        SharedPreferences.Editor editor = allCitiesSharedPreferences.edit();
                        editor.putString("allcities", GsonStringConvertor.gsonToString(response.body().response));
                        allcitiesLiveData.setValue(response.body().response.cities);
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllCitiesApiResponse> call, Throwable t) {

            }
        });
    }

    void refreshHotelCitiesData(String access_token, String admin_id) {
        allCitiesAPI.getAllHotelCities(access_token, admin_id).enqueue(new Callback<AllCitiesApiResponse>() {
            @Override
            public void onResponse(Call<AllCitiesApiResponse> call, Response<AllCitiesApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response != null) {
                        SharedPreferences.Editor editor = allCitiesSharedPreferences.edit();
                        editor.putString("cities_hotel", GsonStringConvertor.gsonToString(response.body().response));
                        hotelCitiesLiveData.setValue(response.body().response.cities);
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllCitiesApiResponse> call, Throwable t) {

            }
        });
    }
}
