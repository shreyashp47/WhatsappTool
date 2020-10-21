package com.cotrav.testalarm.repos;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.cotrav.testalarm.data.CallRoomDatabase;
import com.cotrav.testalarm.data.LoginApiResponce;
import com.cotrav.testalarm.retrofit.ConfigRetrofit;
import com.cotrav.testalarm.retrofit.PhoneNumAPI;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginRepo {
    CallRoomDatabase callRoomDatabase;
    private static final String TAG = "LoginRepo";
    SharedPreferences loginPref, preferences, apicallPrf;
    PhoneNumAPI phoneNumAPI;


    MutableLiveData<String> loginSuccess;


    public LoginRepo(Application application) {
        phoneNumAPI = ConfigRetrofit.configRetrofit(PhoneNumAPI.class);
        loginPref = application.getSharedPreferences("login", MODE_PRIVATE);
        preferences = application.getSharedPreferences("all_phone_no", MODE_PRIVATE);
        apicallPrf = application.getSharedPreferences("api_call", MODE_PRIVATE);
        loginSuccess = new MutableLiveData<>();
        callRoomDatabase = CallRoomDatabase.getDatabase(application);

    }

    public MutableLiveData<String> getLoginSuccess() {
        return loginSuccess;
    }

    public MutableLiveData<String> getLoginSuccess(String mobile_no) {
        JsonObject json = new JsonObject();
        json.addProperty("phone", mobile_no);
        phoneNumAPI.login(json).enqueue(new Callback<LoginApiResponce>() {
            @Override
            public void onResponse(Call<LoginApiResponce> call, Response<LoginApiResponce> response) {
                if (response.isSuccessful())
                    if (response.body().getUserData() != null) {

                        SharedPreferences.Editor editor1 = apicallPrf.edit();
                        editor1.putString("api_call", "fetch").apply();
                        SharedPreferences.Editor editor = loginPref.edit();
                        editor.putString("users_id", response.body().getUserData().getUsersId());
                        editor.putString("status", response.body().getUserData().getStatus());
                        editor.putString("name", response.body().getUserData().getName());
                        editor.putString("token", response.body().getUserData().getToken());
                        editor.putString("login_status", "active");
                        editor.putString("user_type", response.body().getUserData().getUserType());
                        editor.apply();
                        loginSuccess.setValue("success");
                    } else loginSuccess.setValue("error");
                else loginSuccess.setValue("error");

            }

            @Override
            public void onFailure(Call<LoginApiResponce> call, Throwable t) {
                loginSuccess.setValue("error");
            }
        });
        return loginSuccess;
    }

    public void logout() {
        SharedPreferences.Editor editor = loginPref.edit();
        SharedPreferences.Editor editor1 = preferences.edit();
        SharedPreferences.Editor editor2 = apicallPrf.edit();

        editor1.clear().apply();
        editor2.clear().apply();
        editor.clear();
        editor.apply();
        callRoomDatabase.daoRecord().deleteAll();
        callRoomDatabase.phoneNumbersDao().deleteAll();
    }
}
