package com.trinket.corporate.gifting.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.trinket.corporate.gifting.model.login.LoginApiResponse;
import com.trinket.corporate.gifting.retrofit.ConfigRetrofit;
import com.trinket.corporate.gifting.retrofit.GsonStringConvertor;
import com.trinket.corporate.gifting.retrofit.LoginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {
    public static LoginRepository INSTANCE;
    private MutableLiveData<LoginApiResponse> loginApiResponseMutableLiveData;
    private boolean isLogin;
    private LoginApi loginApi;
    private MutableLiveData<String> loginStatus;
    private MutableLiveData<String> logout;
    private SharedPreferences sharedPreferences;
    private MutableLiveData<String> accessToken, error;

    public LoginRepository(Application application) {
        loginApi = ConfigRetrofit.configRetrofit(LoginApi.class);
        error = new MutableLiveData<>();
        sharedPreferences = application.getSharedPreferences("loginPref", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);

        loginApiResponseMutableLiveData = new MutableLiveData<>();

        accessToken = new MutableLiveData<>();
        loginStatus = new MutableLiveData<>();
        logout = new MutableLiveData<>();
        if (sharedPreferences.getString("loginInfo", null) != null) {
            loginApiResponseMutableLiveData.setValue(GsonStringConvertor.stringToGson(sharedPreferences.getString("loginInfo", null), LoginApiResponse.class));
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


    public void performLogin(String phoneNo, String pass) {
        loginApi.performLogin(phoneNo, pass).enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        loginApiResponseMutableLiveData.setValue(response.body());
                        accessToken.setValue(response.body().getToken());
                        loginStatus.setValue("success");

                        sharedPreferences.edit().putBoolean("isLogin", true)
                                .putString("accessToken", accessToken.getValue())
                                .putString("loginInfo", GsonStringConvertor.gsonToString(loginApiResponseMutableLiveData.getValue()))
                                .apply();
                    } else {
                        error.setValue("Connection Error: " + response.message());
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
        loginStatus.setValue("unsuccess");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        logout.setValue("logout");


    }

    public MutableLiveData<String> getLogout() {
        return logout;
    }

    public MutableLiveData<LoginApiResponse> getLoginApiResponseMutableLiveData() {
        return loginApiResponseMutableLiveData;
    }

    public void setLoginApiResponseMutableLiveData(MutableLiveData<LoginApiResponse> loginApiResponseMutableLiveData) {
        this.loginApiResponseMutableLiveData = loginApiResponseMutableLiveData;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setLogout(MutableLiveData<String> logout) {
        this.logout = logout;
    }

    public MutableLiveData<String> getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(MutableLiveData<String> accessToken) {
        this.accessToken = accessToken;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void setError(MutableLiveData<String> error) {
        this.error = error;
    }

    public MutableLiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(MutableLiveData<String> loginStatus) {
        this.loginStatus = loginStatus;
    }
}
