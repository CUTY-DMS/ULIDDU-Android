package com.example.wetoo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitClient instance = null;
    private static ServiceApi serviceApi;

    private final static String BASE_URL = "http://35.227.157.193:8080/";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    private static Retrofit getClient() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        }
        return retrofit;

    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
    public static ServiceApi getRetrofitInterface() {
        return serviceApi;
    }
}
