package com.taxivaxi.approver.repository;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;

import com.taxivaxi.approver.models.analyticsmodel.AnalyticsApiResponse;
import com.taxivaxi.approver.models.analyticsmodel.TotalBookings;
import com.taxivaxi.approver.retrofit.AnalyticsAPI;
import com.taxivaxi.approver.retrofit.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sandeep on 21/7/17.
 */

public class AnalyticsRepository {
    private AnalyticsAPI analyticsAPI;
    private MutableLiveData<TotalBookings> authLiveAnalyticsData;
    private Application application;
    private MutableLiveData<String> error;

    public AnalyticsRepository(Application application){
        this.application=application;
        analyticsAPI= ConfigRetrofit.configRetrofit(AnalyticsAPI.class);
        authLiveAnalyticsData=new MutableLiveData<>();
        error=new MutableLiveData<>();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<TotalBookings> getAuthLiveAnalyticsData(String access_token, String type) {
        getAnalyticsData(access_token,type);
        return authLiveAnalyticsData;
    }

    public void getAnalyticsData(String access_token, String type){

        if (type.equals("Approver 1")){
            analyticsAPI.getAuthOneAnalytics(access_token).enqueue(new Callback<AnalyticsApiResponse>() {
                @Override
                public void onResponse(Call<AnalyticsApiResponse> call, Response<AnalyticsApiResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body().success.equals("1")){
                            authLiveAnalyticsData.setValue(response.body().response.totalBookings);
                        }
                        else if (!response.body().error.equals("")){
                            error.setValue(response.body().error);
                        }
                        else {
                            error.setValue("Connection Error");
                        }
                    }
                    else {
                        error.setValue("Connection Error");
                    }
                }

                @Override
                public void onFailure(Call<AnalyticsApiResponse> call, Throwable t) {
                    error.setValue("Connection Error");
                }
            });
        }
        else if (type.equals("Approver 2")){
            analyticsAPI.getAuthtwoAnalytics(access_token).enqueue(new Callback<AnalyticsApiResponse>() {
                @Override
                public void onResponse(Call<AnalyticsApiResponse> call, Response<AnalyticsApiResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body().success.equals("1")){
                            authLiveAnalyticsData.setValue(response.body().response.totalBookings);
                        }
                        else if (!response.body().error.equals("")){
                            error.setValue(response.body().error);

                        }
                        else {
                            error.setValue("Connection Error");
                        }
                    }
                    else {
                        error.setValue("Connection Error ");
                    }
                }

                @Override
                public void onFailure(Call<AnalyticsApiResponse> call, Throwable t) {
                    error.setValue("Connection Error");
                }
            });
        }
    }

}
