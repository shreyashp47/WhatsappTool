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
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCity;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCityApiResponse;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCityResponse;
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

public class AssessmentCitiesRepository {
    Application application;
    SharedPreferences assessmentCitiespreference;
    SharedPreferences assessCitiespreference;

    AllCitiesAPI assesmentcitiesAPI;
    MutableLiveData<List<City>> assessmentCityLiveData;
    MutableLiveData<List<AssCity>> assessCityLiveData;

    MutableLiveData<List<City>> hotelAssessmentCities;

    public AssessmentCitiesRepository(Application application){
        this.application=application;
        assessmentCitiespreference=application.getSharedPreferences("assessment_cities", Context.MODE_PRIVATE);
        assessCitiespreference=application.getSharedPreferences("assess_cities",Context.MODE_PRIVATE);
        assesmentcitiesAPI= ConfigRetrofit.configRetrofit(AllCitiesAPI.class);
        assessmentCityLiveData=new MutableLiveData<>();
        assessCityLiveData=new MutableLiveData<>();
        hotelAssessmentCities=new MutableLiveData<>();


        if (!assessCitiespreference.getString("assess_cities","n").equals("n")){
            AssCityResponse cities_resp= GsonStringConvertor.stringToGson(assessmentCitiespreference.getString("assess_cities","n"), AssCityResponse.class);
            assessCityLiveData.setValue(cities_resp.cities);
        }


        if (!assessmentCitiespreference.getString("assessment_cities","n").equals("n"))
        {
            CitiesResponse citiesResponse= GsonStringConvertor.stringToGson(assessmentCitiespreference.getString("assessment_cities","n"),CitiesResponse.class);
            assessmentCityLiveData.setValue(citiesResponse.cities);
        }
        if (!assessmentCitiespreference.getString("assessment_cities_hotels","n").equals("n")){
            CitiesResponse citiesResponse= GsonStringConvertor.stringToGson(assessmentCitiespreference.getString("assessment_cities_hotels","n"),CitiesResponse.class);
            hotelAssessmentCities.setValue(citiesResponse.cities);
        }
    }

    public MutableLiveData<List<City>> getHotelAssessmentCities(String access_token, String admin_id) {
        refreshHotelAssessmentCities(access_token, admin_id);
        return hotelAssessmentCities;
    }

//    public LiveData<List<City>> getAssessmentCities(String access_token, String admin_id){
//
//            refreshAssessmentCities(access_token,admin_id);
//        return assessmentCityLiveData;
//    }
//
//
//
//
//
//    private void refreshAssessmentCities(String access_token,String admin_id){
//        assesmentcitiesAPI.getAllAssessmentCities(access_token,admin_id,"Taxivaxi").enqueue(new Callback<AllCitiesApiResponse>() {
//            @Override
//            public void onResponse(Call<AllCitiesApiResponse> call, Response<AllCitiesApiResponse> response) {
//                if (response.isSuccessful()){
//                    if (response.body().response!=null){
//
//                        Log.d("Ass Cities",response.body().toString());
//                        SharedPreferences.Editor editor=assessmentCitiespreference.edit();
//                        editor.putString("assessment_cities",GsonStringConvertor.gsonToString(response.body().response));
//                        assessmentCityLiveData.setValue(response.body().response.cities);
//                        editor.commit();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllCitiesApiResponse> call, Throwable t) {
//
//            }
//        });
//    }


     public LiveData<List<AssCity>> getAssessCities(String access_token, String admin_id){

         refreshAssCities(access_token,admin_id);
        return assessCityLiveData;
    }


    private void refreshAssCities(String access_token,String admin_id){
        Log.d("data_cities","access"+access_token+"id"+admin_id);
        assesmentcitiesAPI.getAllAssCities(access_token,"1").enqueue(new Callback<AssCityApiResponse>() {
            @Override
            public void onResponse(Call<AssCityApiResponse> call, Response<AssCityApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().response!=null){
                        SharedPreferences.Editor editor=assessmentCitiespreference.edit();
                        editor.putString("assess_cities",GsonStringConvertor.gsonToString(response.body().response));
                        assessCityLiveData.setValue(response.body().response.cities);
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<AssCityApiResponse> call, Throwable t) {

            }
        });
    }


    private void refreshHotelAssessmentCities(String access_token,String admin_id){
        assesmentcitiesAPI.getAllHotelAssessmentCities(access_token,admin_id,"Taxivaxi").enqueue(new Callback<AllCitiesApiResponse>() {
            @Override
            public void onResponse(Call<AllCitiesApiResponse> call, Response<AllCitiesApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().response!=null){
                        SharedPreferences.Editor editor=assessmentCitiespreference.edit();
                        editor.putString("assessment_cities_hotels",GsonStringConvertor.gsonToString(response.body().response));
                        hotelAssessmentCities.setValue(response.body().response.cities);
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