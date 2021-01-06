package com.taxivaxi.operator.retrofit;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sandeep on 6/7/17.
 */

public class ConfigRetrofit {
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(ApiURLs.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(configOkhttp())
            .build();

    private static Retrofit googleApiRetrofit=new Retrofit.Builder()
            .baseUrl(ApiURLs.googleApiBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(configOkhttp())
            .build();

    public static <S> S configRetrofit(Class<S> service){
        return retrofit.create(service);
    }

    public static <S> S configGoogleRetrofit(Class<S> service){
        return googleApiRetrofit.create(service);
    }

    private static OkHttpClient configOkhttp(){
        OkHttpClient.Builder okHttpClientbuilder=new OkHttpClient.Builder();
        okHttpClientbuilder.addInterceptor(configOkHttpLoggingIncepter());
        return okHttpClientbuilder.build();

    }

    private static HttpLoggingInterceptor configOkHttpLoggingIncepter(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
