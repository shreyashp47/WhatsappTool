package com.cotrav.testalarm.repos;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cotrav.testalarm.data.CallRoomDatabase;
import com.cotrav.testalarm.data.UploadLeadResponce;
import com.cotrav.testalarm.data.phoneno.PhoneNumbers;
import com.cotrav.testalarm.data.phoneno.PhoneResponce;
import com.cotrav.testalarm.other.GsonStringConvertor;
import com.cotrav.testalarm.retrofit.ConfigRetrofit;
import com.cotrav.testalarm.retrofit.PhoneNumAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FetchPhoneRepository {
    Application application;
    SharedPreferences phoneSp;
    PhoneNumbers phoneNumbers;
    private LiveData<List<String>> phoneNoList;
    MutableLiveData<String> string;
    MutableLiveData<String> uploadLead;
    PhoneNumAPI phoneNumAPI;
    CallRoomDatabase callRoomDatabase;
    private LiveData<List<PhoneNumbers>> phoneNumberLiveList;


    public FetchPhoneRepository(Application application) {
        this.application = application;
        phoneNoList = new MutableLiveData<>();
        uploadLead = new MutableLiveData<>();
        phoneNumberLiveList = new MutableLiveData<>();
        phoneNumAPI = ConfigRetrofit.configRetrofit(PhoneNumAPI.class);
        callRoomDatabase = CallRoomDatabase.getDatabase(application);
        string = new MutableLiveData<>();


        phoneSp = application.getSharedPreferences("all_phone_no", MODE_PRIVATE);
        if (!phoneSp.getString("all_phone_no", "n").equals("n")) {
            phoneNumberLiveList = callRoomDatabase.phoneNumbersDao().getAll();
            phoneNumbers = GsonStringConvertor.stringToGson(phoneSp.getString("all_phone_no", "n"), PhoneNumbers.class);

        }

    }

    public MutableLiveData<String> getUploadLead() {
        return uploadLead;
    }

    public MutableLiveData<String> getUploadLead(JsonArray jsonElements) {

        phoneNumAPI.insertCall(jsonElements).enqueue(new Callback<UploadLeadResponce>() {
            @Override
            public void onResponse(Call<UploadLeadResponce> call, Response<UploadLeadResponce> response) {
                if (response.isSuccessful())
                    if (response.body().getSuccess().getText().equals("success")) {
                        callRoomDatabase.daoRecord().deleteAll();
                        uploadLead.setValue("success");
                    } else uploadLead.setValue("error");
                else uploadLead.setValue("error");
            }

            @Override
            public void onFailure(Call<UploadLeadResponce> call, Throwable t) {
                uploadLead.setValue("error");
            }
        });


        return uploadLead;
    }

    public LiveData<String> getPhoneNoList(JsonObject jsonObject) {
        phoneNumAPI.getPhoneNumbers(jsonObject).enqueue(new Callback<PhoneResponce>() {
            @Override
            public void onResponse(Call<PhoneResponce> call, Response<PhoneResponce> response) {
                if (response.isSuccessful()) {
                    if (!phoneSp.getString("all_phone_no", "n").equals(GsonStringConvertor.gsonToString(response.body()))) {
                        callRoomDatabase.phoneNumbersDao().deleteAll();
                        for (int i = 0; i < response.body().getResponse().size(); i++) {
                            callRoomDatabase.phoneNumbersDao().insert(response.body().getResponse().get(i));
                        }
                        SharedPreferences.Editor editor = phoneSp.edit();
                        editor.putString("all_phone_no", GsonStringConvertor.gsonToString(response.body()));
                        editor.commit();
                    }
                    string.setValue("OK");

                }
            }

            @Override
            public void onFailure(Call<PhoneResponce> call, Throwable t) {
                Log.d("error", t.getMessage());
                string.setValue("ERROR");
            }
        });

        return string;
    }


}
