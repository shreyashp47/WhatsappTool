package com.cotrav.testalarm.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cotrav.testalarm.repos.LoginRepo;


public class LoginViewModel extends AndroidViewModel {

    MutableLiveData<String> loginSuccess;
    LoginRepo loginRepo;



    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepo=new LoginRepo(application);
        loginSuccess=loginRepo.getLoginSuccess();
    }

    public MutableLiveData<String> getLoginSuccess() {
        loginSuccess=loginRepo.getLoginSuccess();
        return loginSuccess;
    }

    public MutableLiveData<String> getLoginSuccess(String mobile_no) {
        loginSuccess=loginRepo.getLoginSuccess(mobile_no);
        return loginSuccess;
    }

    public void setLoginSuccess(MutableLiveData<String> loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public void loguot(){
        loginRepo.logout();
    }
}
