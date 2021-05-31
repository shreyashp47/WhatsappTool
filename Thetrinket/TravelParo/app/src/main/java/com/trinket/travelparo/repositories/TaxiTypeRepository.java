package com.taxivaxi.spoc.repository;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;

import com.taxivaxi.spoc.model.taxitypemodel.Response;
import com.taxivaxi.spoc.model.taxitypemodel.TaxiType;
import com.taxivaxi.spoc.model.taxitypemodel.TaxiTypeApiResponse;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.retrofit.TaxiTypeAPI;
import com.taxivaxi.spoc.utility.GsonStringConvertor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sandeep on 28/9/17.
 */

public class TaxiTypeRepository {

    MutableLiveData<List<TaxiType>> taxiTypes;
    SharedPreferences taxiTypePref;
    TaxiTypeAPI taxiTypeAPI;

    public TaxiTypeRepository(Application application) {
        taxiTypes=new MutableLiveData<>();
        taxiTypeAPI= ConfigRetrofit.configRetrofit(TaxiTypeAPI.class);
        taxiTypePref=(SharedPreferences)application.getSharedPreferences("taxitypeinfo", Context.MODE_PRIVATE);
        if (!taxiTypePref.getString("taxi_types","n").equals("n")){
            taxiTypes.setValue(GsonStringConvertor.stringToGson(taxiTypePref.getString("taxi_types","n"), Response.class).getTaxiTypes());
        }
    }

    public MutableLiveData<List<TaxiType>> getTaxiTypes(String accessToken,String adminId) {

        taxiTypeAPI.getTaxiTypes(accessToken,adminId).enqueue(new Callback<TaxiTypeApiResponse>() {
            @Override
            public void onResponse(Call<TaxiTypeApiResponse> call, retrofit2.Response<TaxiTypeApiResponse> response) {
                SharedPreferences.Editor editor=taxiTypePref.edit();
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("1") && response.body().getResponse()!=null){
                        taxiTypes.setValue(response.body().getResponse().getTaxiTypes());
                        editor.putString("taxi_types",GsonStringConvertor.gsonToString(response.body().getResponse()));
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<TaxiTypeApiResponse> call, Throwable t) {

            }
        });
        return taxiTypes;
    }
}
