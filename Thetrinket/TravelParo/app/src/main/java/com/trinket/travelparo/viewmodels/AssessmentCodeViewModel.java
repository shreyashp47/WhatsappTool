package com.taxivaxi.spoc.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taxivaxi.spoc.model.assesmentcodemodel.AssCode;
import com.taxivaxi.spoc.repository.AssessmentCodeRepository;

import java.util.List;

/**
 * Created by Baliram on 07/7/19.
 */

public class AssessmentCodeViewModel extends AndroidViewModel {
    LiveData<List<AssCode>> assessmentCodeInfo;

    AssessmentCodeRepository assessmentCodeRepository;

    public AssessmentCodeViewModel(Application application)
    {
        super(application);
        assessmentCodeRepository=new AssessmentCodeRepository(this.getApplication());
    }

    public void initAssessmentCode(String access_token, String admin_id){
        assessmentCodeInfo =assessmentCodeRepository.getAssessmentCode(access_token,admin_id);
    }

    public LiveData<List<AssCode>> getAssessmentCodeInfo(String access_token, String admin_id) {
        if (assessmentCodeInfo ==null) {
            assessmentCodeInfo = assessmentCodeRepository.getAssessmentCode(access_token, admin_id);
        }
        return assessmentCodeInfo;
    }
}
