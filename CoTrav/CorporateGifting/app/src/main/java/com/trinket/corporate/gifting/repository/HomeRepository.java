package com.trinket.corporate.gifting.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.trinket.corporate.gifting.model.home.CategoriesApiResponse;
import com.trinket.corporate.gifting.retrofit.ConfigRetrofit;
import com.trinket.corporate.gifting.retrofit.HomeApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    HomeApi homeApi;
    MutableLiveData<List<CategoriesApiResponse>> categoryList;

    public HomeRepository(Application application) {
        homeApi = ConfigRetrofit.configRetrofit(HomeApi.class);
        categoryList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CategoriesApiResponse>> getCategoryList(String token) {

        homeApi.getCategories("Bearer " + token).enqueue(new Callback<List<CategoriesApiResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriesApiResponse>> call, Response<List<CategoriesApiResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        categoryList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CategoriesApiResponse>> call, Throwable t) {

            }
        });
        return categoryList;
    }

    public MutableLiveData<List<CategoriesApiResponse>> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(MutableLiveData<List<CategoriesApiResponse>> categoryList) {
        this.categoryList = categoryList;
    }
}
