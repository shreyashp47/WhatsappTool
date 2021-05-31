package com.taxivaxi.thetrinketadmin.retrofit;



import com.taxivaxi.thetrinketadmin.model.products.ProductApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProductApi {

    @FormUrlEncoded
    @POST(ApiUrl.get_products)
    Call<ProductApiResponse> getProducts(
            @Header("Authorization") String authorization,
            @Field("categories_id") String categories_id,
            @Field("page") Integer page,
            @Field("limit") String limit
    );
}
