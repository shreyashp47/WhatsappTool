package com.taxivaxi.spoc.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.taxitypemodel.TaxiType;
import com.taxivaxi.spoc.repository.TaxiTypeRepository;

import java.util.List;

/**
 * Created by sandeep on 9/10/17.
 */

public class TaxiTypeViewModel extends AndroidViewModel {

    TaxiTypeRepository taxiTypeRepository;
    LiveData<List<TaxiType>> taxiTypes;

    public TaxiTypeViewModel(Application application) {
        super(application);
        taxiTypeRepository=new TaxiTypeRepository(application);
    }

    public void initViewModel(String accessToken,String adminId){
        if (taxiTypes==null) {
            taxiTypes = taxiTypeRepository.getTaxiTypes(accessToken, adminId);
        }
    }

    public LiveData<List<TaxiType>> getTaxiTypes() {
        return taxiTypes;
    }
}
