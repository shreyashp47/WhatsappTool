package com.trinket.travelparo.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

public class ChangeUserRepository {


    private static final String TAG = "ChangeUserRepository";
    SharedPreferences sharedPreferences;
    private boolean is_login;
    MutableLiveData<String> currentUser;


    public ChangeUserRepository(Application application) {
        currentUser = new MutableLiveData<>();
        sharedPreferences = application.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        is_login = sharedPreferences.getBoolean("is_login", false);
        currentUser.setValue(sharedPreferences.getString("current_user", "n"));


        /*  if (is_login) {
            Log.d(TAG, employeePreference.getString("access_token", "n"));
            access_token.setValue(employeePreference.getString("access_token", "n"));
            employee.setValue(GsonStringConvertor.stringToGson(employeePreference.getString("employee_info", "n"), Employee.class));
        }*/
    }



    public MutableLiveData<String> changeUser(String user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_user", user);
        editor.apply();
        currentUser.setValue(user);

        return currentUser;
    }

    public MutableLiveData<String> getCurrentUser() {
        return currentUser;
    }
}
