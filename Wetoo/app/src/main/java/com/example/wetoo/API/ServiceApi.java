package com.example.wetoo.API;

import com.example.wetoo.LoginRegister.LoginRequest;
import com.example.wetoo.LoginRegister.LoginResponse;
import com.example.wetoo.LoginRegister.RegisterRequest;
import com.example.wetoo.UserInfo.MyInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("login") // 로그인
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
    );

    @POST("register") // 회원가입
    Call<Void> register(
            @Body RegisterRequest registerRequest
    );

    @GET("user") // 내 정보 조회
    Call<MyInfoResponse> userInfo(
            String userName, String userId, int userAge
    );


}
