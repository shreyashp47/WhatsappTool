package com.taxivaxi.operator.viewmode;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.operator.model.login.Operator;
import com.taxivaxi.operator.repository.LoginRepository;


public class DriverInfoViewModel extends AndroidViewModel {

    LiveData<String> error;
    LiveData<Operator> driver;
    LiveData<String> loginStatus, logout;
    LiveData<String> accessToken;

    LoginRepository loginRepository;


    public DriverInfoViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
        error = loginRepository.getError();
        loginStatus = loginRepository.getLoginStatus();

        driver = loginRepository.getDriver();
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

    public LiveData<Operator> getOperator() {
        return driver;
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
