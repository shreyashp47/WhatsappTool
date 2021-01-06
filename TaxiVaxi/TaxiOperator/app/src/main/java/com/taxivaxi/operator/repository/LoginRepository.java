package com.taxivaxi.operator.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.taxivaxi.operator.GsonStringConvertor;
import com.taxivaxi.operator.model.login.Operator;
import com.taxivaxi.operator.model.login.LoginApiResponse;
import com.taxivaxi.operator.retrofit.ConfigRetrofit;
import com.taxivaxi.operator.retrofit.LoginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {
    public static LoginRepository INSTANCE;
    private MutableLiveData<Operator> driver;
    private boolean isLogin;
    private LoginApi loginApi;
    private MutableLiveData<String> loginStatus;
    private SharedPreferences sharedPreferences;
    private MutableLiveData<String> error;
    private MutableLiveData<String> verificationStatus;
    private MutableLiveData<String> logout;
    private MutableLiveData<String> accessToken;

    public LoginRepository(Application application) {
        driver = new MutableLiveData<>();
        loginStatus = new MutableLiveData<>();
        loginApi = ConfigRetrofit.configRetrofit(LoginApi.class);
        error = new MutableLiveData<>();
        sharedPreferences = application.getSharedPreferences("driverPref", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        verificationStatus = new MutableLiveData<>();
        logout = new MutableLiveData<>();
        accessToken = new MutableLiveData<>();
        if (sharedPreferences.getString("driverInfo", null) != null) {
            driver.setValue(GsonStringConvertor.stringToGson(sharedPreferences.getString("driverInfo", null), Operator.class));
            accessToken.setValue(sharedPreferences.getString("accessToken", null));
        }
    }

    public static LoginRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository(application);
        }
        return INSTANCE;
    }

    public boolean getIsLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    public MutableLiveData<Operator> getDriver() {
        return driver;
    }

    public MutableLiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<String> getAccessToken() {
        return accessToken;
    }

    public MutableLiveData<String> getVerificationStatus() {
        return verificationStatus;
    }


    public void performLogin(String phoneNo, String pass) {
        loginApi.performLogin(phoneNo, pass).enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1") && response.body().getResponse() != null) {
                        driver.setValue(response.body().getResponse().getOperator());
                        accessToken.setValue(response.body().getResponse().getAccessToken());
                        loginStatus.setValue("successful");
                        sharedPreferences.edit().putBoolean("isLogin", true)
                                .putString("accessToken", accessToken.getValue())
                                .putString("driverInfo", GsonStringConvertor.gsonToString(driver.getValue()))
                                .apply();
                    } else {
                        error.setValue(response.body().getError());
                    }
                } else {
                    error.setValue("Connection Error");
                }
            }

            @Override
            public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                error.setValue("Connection Error: " + t.getMessage());
            }
        });
    }


    public void Logout() {
        loginStatus.setValue("unsuccessful");
        verificationStatus.setValue("unsuccessful");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        logout.setValue("logout");


    }

    public MutableLiveData<String> getLogout() {
        return logout;
    }


}
