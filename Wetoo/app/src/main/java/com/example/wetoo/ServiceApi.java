package com.example.wetoo;

import android.app.DownloadManager;
import android.icu.text.CaseMap;

import com.example.wetoo.API.LoginRequest;
import com.example.wetoo.API.LoginResponse;
import com.example.wetoo.API.RegisterRequest;
import com.example.wetoo.API.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/login") // 로그인
    Call<LoginResponse> Login(
            @Body LoginRequest loginRequest
    );

    @POST("/register") // 회원가입
    Call<RegisterResponse> Register(
            @Body RegisterRequest registerRequest
    );

}
