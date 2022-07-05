package com.example.wetoo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static Retrofit instance;
    private static String BASE_URL = "http://35.227.157.193:8080/";

    public static Retrofit getInstance() {
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
