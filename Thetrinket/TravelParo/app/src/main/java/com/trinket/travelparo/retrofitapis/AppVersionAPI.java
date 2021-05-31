package com.taxivaxi.employee.retrofitapis;




import com.taxivaxi.employee.models.apkversionmodel.ApkVersionApiResponse;

import retrofit2.Call;
import retrofit2.http.POST;


public interface AppVersionAPI {

    @POST(APIurls.APK_VERSION_URL)
    Call<ApkVersionApiResponse> getMinimumSupportedApkVersion();
}
