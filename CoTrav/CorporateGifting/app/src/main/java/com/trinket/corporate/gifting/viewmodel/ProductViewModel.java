package com.trinket.corporate.gifting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.trinket.corporate.gifting.model.products.ProductApiResponse;
import com.trinket.corporate.gifting.repository.ProductsRepository;


public class ProductViewModel extends AndroidViewModel {

    ProductsRepository homeRepository;
    LiveData<ProductApiResponse> listLiveData;
    LiveData<String> error;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new ProductsRepository(getApplication());
        listLiveData = homeRepository.getProductList();

    }

    public LiveData<ProductApiResponse> getListLiveData(String token, String category_id, Integer page) {
        return listLiveData = homeRepository.getProductsList(token, category_id, page);
    }

    public LiveData<String> getError() {
        return error;
    }


}
