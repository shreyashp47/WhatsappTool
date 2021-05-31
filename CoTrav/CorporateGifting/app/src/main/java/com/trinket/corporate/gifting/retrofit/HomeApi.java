package com.trinket.corporate.gifting.retrofit;

import com.trinket.corporate.gifting.model.home.CategoriesApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HomeApi {

    @POST(ApiUrl.get_all_categories)
    Call<List<CategoriesApiResponse>> getCategories(
            @Header("Authorization") String authorization
    );
}
