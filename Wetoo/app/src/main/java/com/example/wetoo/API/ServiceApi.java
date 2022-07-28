package com.example.wetoo.API;

<<<<<<< Updated upstream
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
import com.example.wetoo.Request.BoardRequest;
import com.example.wetoo.Request.TodoRequest;
import com.example.wetoo.Response.DetailResponse;
import com.example.wetoo.Request.LoginRequest;
import com.example.wetoo.Response.LoginResponse;
import com.example.wetoo.Request.RegisterRequest;
import com.example.wetoo.Response.SearchResponse;
import com.example.wetoo.Request.EditRequest;
import com.example.wetoo.Response.TodoResponse;
import com.example.wetoo.Response.MyInfoResponse;

import java.util.ArrayList;
import java.util.List;
<<<<<<< Updated upstream
=======
import com.example.wetoo.Board.BoardRequest;
import com.example.wetoo.DetailResponse;
=======
>>>>>>> Stashed changes
import com.example.wetoo.LoginRegister.LoginRequest;
import com.example.wetoo.LoginRegister.LoginResponse;
import com.example.wetoo.LoginRegister.RegisterRequest;
import com.example.wetoo.Search.SearchResponse;
import com.example.wetoo.TodoResponse;
import com.example.wetoo.UserInfo.MyInfoResponse;
>>>>>>> main

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
    Call<BoardRequest> board(
            @Header("Authorization") String token,
            @Body BoardRequest boardRequest
    );

    @GET("search/{userid}") // 유저 정보 검색
    Call<List<SearchResponse>> Search(
            @Header("Authorization") String token,
            @Header("X-Refresh-Token") String refreshToken
    );

<<<<<<< Updated upstream
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
    @GET("todo/list/user/{id}") // 유저 투두리스트
    Call<ArrayList<TodoResponse>> todo(
            @Header("Authorization") String token,
            @Body TodoRequest todoRequest
    );

<<<<<<< Updated upstream
=======
>>>>>>> main
=======
>>>>>>> Stashed changes
    @GET("todo/{id}")   // 투두 상세 보기
    Call<DetailResponse> detail(
            @Header("Authorization") String token
    );

<<<<<<< Updated upstream
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
    @PATCH("todo/{id}")
    Call<Void> edit(
            @Header("Authorization") String token,
            @Body EditRequest editRequest
    );

    @DELETE("todo/{id}")
    Call<Void> delete(
            @Header("Authorization") String token
    );

    @PUT("todo/{id}")
    Call<Void> success(
            @Header("Authorization") String token
    );


<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
    @GET("todo/list/user/{id}")
    Call<TodoResponse> todo(
            @Header("Authorization") String token
    );

>>>>>>> main
}
