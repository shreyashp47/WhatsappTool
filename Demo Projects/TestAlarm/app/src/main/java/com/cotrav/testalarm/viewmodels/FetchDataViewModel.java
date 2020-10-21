package com.cotrav.testalarm.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.cotrav.testalarm.data.OfflineDatabase;
import com.cotrav.testalarm.repos.FetchDataRepository;

import java.util.List;

public class FetchDataViewModel extends AndroidViewModel {

    LiveData<List<OfflineDatabase>> offlineDataList;
    FetchDataRepository fetchDataRepository;

    public FetchDataViewModel(@NonNull Application application) {
        super(application);

        fetchDataRepository = new FetchDataRepository(this.getApplication());

        offlineDataList = fetchDataRepository.getOfflineDatabaseMutableLiveData();

    }


    public LiveData<List<OfflineDatabase>> getOfflineDataList() {
        offlineDataList=fetchDataRepository.getOfflineDatabaseMutableLiveData();
        return offlineDataList;
    }
    public LiveData<List<OfflineDatabase>> setData() {
        fetchDataRepository.getsetCallData();
        return offlineDataList;
    }

    public void setOfflineDataList(LiveData<List<OfflineDatabase>> offlineDataList) {

        this.offlineDataList = offlineDataList;
    }

}
