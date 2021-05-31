package com.taxivaxi.employee.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.taxivaxi.employee.BuildConfig;
import com.taxivaxi.employee.models.apkversionmodel.ApkVersionApiResponse;
import com.taxivaxi.employee.retrofitapis.AppVersionAPI;
import com.taxivaxi.employee.retrofitapis.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApkVersionRepository {
    private MutableLiveData<String> successStatus;
    private AppVersionAPI appVersionAPI;

    public ApkVersionRepository(){
        successStatus=new MutableLiveData<>();
        appVersionAPI= ConfigRetrofit.configRetrofit(AppVersionAPI.class);
    }

    public LiveData<String> getSuccessStatus() {
        appVersionAPI.getMinimumSupportedApkVersion().enqueue(new Callback<ApkVersionApiResponse>() {
            @Override
            public void onResponse(Call<ApkVersionApiResponse> call, Response<ApkVersionApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1")){
                        if(BuildConfig.VERSION_CODE< Integer.parseInt(response.body().getResponse().getVersion())){
                            successStatus.setValue("unsuccessful");
                        }
                        else {
                            successStatus.setValue("successful");
                        }
                    }
                    else {
                        successStatus.setValue("Connection Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApkVersionApiResponse> call, Throwable t) {
                successStatus.setValue("Connection Error");
            }
        });
        return successStatus;
    }

}
