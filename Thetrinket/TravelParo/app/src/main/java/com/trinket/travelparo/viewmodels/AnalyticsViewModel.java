package com.taxivaxi.approver.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.taxivaxi.approver.models.analyticsmodel.TotalBookings;
import com.taxivaxi.approver.repository.AnalyticsRepository;

/**
 * Created by sandeep on 21/7/17.
 */

public class AnalyticsViewModel extends AndroidViewModel {

    AnalyticsRepository analyticsRepository;
    LiveData<TotalBookings> authLiveAnalyticsData;
    LiveData<String> error;
    LiveData<String> accessToken;
    LiveData<String> authType;

    public AnalyticsViewModel(Application application) {
        super(application);
        analyticsRepository=new AnalyticsRepository(application);
        error=analyticsRepository.getError();

    }

    public LiveData<TotalBookings> getAuthLiveAnalyticsData(String accessToken, String type) {
        if (authLiveAnalyticsData==null){
            authLiveAnalyticsData=analyticsRepository.getAuthLiveAnalyticsData(accessToken,type);
        }
        return authLiveAnalyticsData;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void refreshAnalyticsData(String accessToken,String type){
        analyticsRepository.getAnalyticsData(accessToken,type);
    }
}
