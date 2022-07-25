package com.example.wetoo.API;

import com.example.wetoo.Board.BoardRequest;
import com.example.wetoo.Board.BoardResponse;
import com.example.wetoo.DetailResponse;
import com.example.wetoo.LoginRegister.LoginRequest;
import com.example.wetoo.LoginRegister.LoginResponse;
import com.example.wetoo.LoginRegister.RegisterRequest;
import com.example.wetoo.Search.SearchResponse;
import com.example.wetoo.UserInfo.MyInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<MyInfoResponse> MyInfo(
            @Header("Authorization") String token
    );

    @POST("todo") // 투두리스트 추가
    Call<BoardResponse> board(
            @Header("Authorization") String token,
            @Header("X-Refresh-Token") String refreshToken,
            @Body BoardRequest boardRequest
    );

    @GET("search/{userid}") // 유저 정보 검색
    Call<SearchResponse> Search(
            @Header("Authorization") String token,
            @Header("X-Refresh-Token") String refreshToken
    );

    @GET("todo/{id}")
    Call<DetailResponse> detail(
            @Header("Authorization") String token
    );
}
