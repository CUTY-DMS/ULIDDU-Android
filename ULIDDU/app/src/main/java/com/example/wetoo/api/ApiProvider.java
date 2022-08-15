package com.example.wetoo.API;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.view.textclassifier.TextLinks;

import com.squareup.okhttp.HttpUrl;

import java.lang.reflect.Field;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://44.209.75.36:8080/";


    public static Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}