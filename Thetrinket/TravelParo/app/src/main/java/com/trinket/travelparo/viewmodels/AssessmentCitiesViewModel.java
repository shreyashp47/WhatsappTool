package com.taxivaxi.spoc.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.allcitiesmodel.City;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCity;
import com.taxivaxi.spoc.repository.AssessmentCitiesRepository;

import java.util.List;

/**
 * Created by sandeep on 17/7/17.
 */

public class AssessmentCitiesViewModel extends AndroidViewModel {
    private LiveData<List<AssCity>> liveAssessCityList;

    private LiveData<List<City>> liveAssessmentCityListHotels;
    AssessmentCitiesRepository assessmentCitiesRepository;

    public AssessmentCitiesViewModel(Application application) {
        super(application);
        assessmentCitiesRepository=new AssessmentCitiesRepository(this.getApplication());
    }

    public void initViewModel(String access_token,String admin_id)
    {
        //liveAssessmentCityList=assessmentCitiesRepository.getAssessmentCities(access_token,admin_id);
        liveAssessCityList=assessmentCitiesRepository.getAssessCities(access_token,admin_id);
    }

    public LiveData<List<AssCity>> getLiveAssessmentCityList(String access_token,String admin_id) {
        if (liveAssessCityList==null)
        {
            //liveAssessmentCityList=assessmentCitiesRepository.getAssessmentCities(access_token,admin_id);
            liveAssessCityList=assessmentCitiesRepository.getAssessCities(access_token,admin_id);

        }
        return liveAssessCityList;
    }
    public LiveData<List<City>> getLiveAssessmentCityListHotels(String access_token,String admin_id) {
        if (liveAssessmentCityListHotels==null){
            liveAssessmentCityListHotels=assessmentCitiesRepository.getHotelAssessmentCities(access_token,admin_id);
        }
        return liveAssessmentCityListHotels;
    }
}
