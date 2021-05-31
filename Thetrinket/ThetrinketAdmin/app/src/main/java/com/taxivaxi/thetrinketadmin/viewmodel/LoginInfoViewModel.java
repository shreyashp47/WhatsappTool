package com.taxivaxi.thetrinketadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.thetrinketadmin.model.login.LoginApiResponse;
import com.taxivaxi.thetrinketadmin.repository.LoginRepository;

public class LoginInfoViewModel extends AndroidViewModel {

    LiveData<String> error;
    LiveData<LoginApiResponse> loginApiResponseLiveData;
    LiveData<String> loginStatus, logout;
    LiveData<String> accessToken;

    LoginRepository loginRepository;


    public LoginInfoViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
        error = loginRepository.getError();


        loginStatus=loginRepository.getLoginStatus();
        loginApiResponseLiveData = loginRepository.getLoginApiResponseMutableLiveData();
        logout = loginRepository.getLogout();
        accessToken = loginRepository.getAccessToken();
    }


    public LiveData<String> getAccessToken() {
        return accessToken;
    }

    public boolean getIsLogin() {
        return loginRepository.getIsLogin();
    }

    public void performLogin(String phoneNo, String pass) {
        loginRepository.performLogin(phoneNo, pass);
    }


    public LiveData<String> getError() {
        return error;
    }

    public LiveData<LoginApiResponse> getOperator() {
        return loginApiResponseLiveData;
    }

    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public void Logout() {
        loginRepository.Logout();

    }



    public LiveData<String> getLogout() {
        return logout;
    }

}
