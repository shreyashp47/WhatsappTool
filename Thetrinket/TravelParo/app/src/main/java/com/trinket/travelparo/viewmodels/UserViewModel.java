package com.trinket.travelparo.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.trinket.travelparo.repositories.UserRepository;

public class ChangeUserViewModel extends AndroidViewModel {


    MutableLiveData<String> currentUser;

    UserRepository userRepository;

    public ChangeUserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        currentUser = userRepository.getCurrentUser();
    }

    public MutableLiveData<String> getCurrentUser(String user) {

        return currentUser = userRepository.changeUser(user);
    }

    public MutableLiveData<String> getCurrentUser() {
        return currentUser;
    }
}
