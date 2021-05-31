package com.taxivaxi.employee.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.employee.repositories.ApkVersionRepository;


/**
 * Created by sandeep on 31/8/17.
 */

public class ApkVersionViewModel extends AndroidViewModel {

    private ApkVersionRepository apkVersionRepository;
    private LiveData<String> successStatus;

    public ApkVersionViewModel(Application application) {
        super(application);
        apkVersionRepository=new ApkVersionRepository();
        successStatus =apkVersionRepository.getSuccessStatus();
    }

    public LiveData<String> getSuccessStatus() {
        return successStatus;
    }

}
