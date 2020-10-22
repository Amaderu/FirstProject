package com.example.firstproject;

import android.app.AlertDialog;
import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdd extends Application {
    private static ApiAdd mInstance;
    private static Retrofit retrofit;
    private static final String BASE_URL="https://petstore.swagger.io/v2/";//"https://jsonplaceholder.typicode.com/";



    private ApiAdd() {
        //Log'ироване
        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);*/

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiAdd getInstance() {
        if (mInstance == null) {
            mInstance = new ApiAdd();
        }
        return mInstance;
    }
    
    public static PostmanApi getApi(){
        return retrofit.create(PostmanApi.class);
    }

}
