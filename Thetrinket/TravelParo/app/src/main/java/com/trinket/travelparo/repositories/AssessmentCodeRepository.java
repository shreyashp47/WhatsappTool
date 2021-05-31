package com.taxivaxi.spoc.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.taxivaxi.spoc.model.assesmentcodemodel.AssCode;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssessmentCodeApiResponse;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssessmentResponse;
import com.taxivaxi.spoc.retrofit.AssessmentCodeAPI;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.utility.GsonStringConvertor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sandeep on 17/7/17.
 */

public class AssessmentCodeRepository {

    private SharedPreferences assessmentcodePreferences;
    private Application application;
    private MutableLiveData<List<AssCode>> assesmentcodes;
    private AssessmentCodeAPI assessmentCodeAPI;

    public AssessmentCodeRepository(Application application){
        this.application=application;
        assessmentcodePreferences=application.getSharedPreferences("assesmentinfo", Context.MODE_PRIVATE);
        assesmentcodes=new MutableLiveData<>();
        assessmentCodeAPI= ConfigRetrofit.configRetrofit(AssessmentCodeAPI.class);
    }

    public LiveData<List<AssCode>> getAssessmentCode(String access_token,String admin_id){
        if (assessmentcodePreferences.getString("assesment_info","n")!="n"){
            AssessmentResponse response= GsonStringConvertor.stringToGson(assessmentcodePreferences.getString("assesment_info","n"),AssessmentResponse.class);
            Log.d("ass_code",GsonStringConvertor.gsonToString(response.assCodes));
            assesmentcodes.setValue(response.assCodes);
        }
        refreshAssessmentCode(access_token,admin_id);

        return assesmentcodes;
    }

    private void refreshAssessmentCode(String access_token,String admin_id){
        assessmentCodeAPI.getAssessmentCodes(access_token,admin_id).enqueue(new Callback<AssessmentCodeApiResponse>() {
            @Override
            public void onResponse(Call<AssessmentCodeApiResponse> call, retrofit2.Response<AssessmentCodeApiResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().response!=null) {
                        SharedPreferences.Editor editor = assessmentcodePreferences.edit();
                        editor.putString("assesment_info", GsonStringConvertor.gsonToString(response.body().response));
                        assesmentcodes.setValue(response.body().response.assCodes);
                        editor.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<AssessmentCodeApiResponse> call, Throwable t) {

            }
        });
    }


}
