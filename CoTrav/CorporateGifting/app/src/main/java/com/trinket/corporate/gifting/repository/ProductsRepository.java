package com.trinket.corporate.gifting.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.trinket.corporate.gifting.model.products.ProductApiResponse;
import com.trinket.corporate.gifting.retrofit.ConfigRetrofit;
import com.trinket.corporate.gifting.retrofit.ProductApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {
    ProductApi productApi;
    MutableLiveData<ProductApiResponse> productList;

    public ProductsRepository(Application application) {
        productApi = ConfigRetrofit.configRetrofit(ProductApi.class);
        productList = new MutableLiveData<>();
    }

    public MutableLiveData<ProductApiResponse> getProductsList(String token,String category_id,Integer page) {

        productApi.getProducts("Bearer " + token,category_id,page,"20").enqueue(new Callback<ProductApiResponse>() {
            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        productList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {

            }
        });
        return productList;
    }

    public MutableLiveData<ProductApiResponse> getProductList() {
        return productList;
    }

    public void setProductList(MutableLiveData<ProductApiResponse> productList) {
        this.productList = productList;
    }
}
