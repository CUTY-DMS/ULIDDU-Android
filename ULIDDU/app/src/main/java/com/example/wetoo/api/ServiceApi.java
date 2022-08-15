package com.example.wetoo.api;

import com.example.wetoo.request.LoginRequest;
import com.example.wetoo.request.MyTodoRequest;
import com.example.wetoo.response.BoardResponse;
import com.example.wetoo.response.LoginResponse;
import com.example.wetoo.request.RegisterRequest;
import com.example.wetoo.response.MyInfoResponse;
import com.example.wetoo.request.BoardRequest;
import com.example.wetoo.request.EditRequest;
import com.example.wetoo.request.TodoRequest;
import com.example.wetoo.response.DetailResponse;
import com.example.wetoo.response.EditResponse;
import com.example.wetoo.response.MyTodoResponse;
import com.example.wetoo.response.SearchResponse;
import com.example.wetoo.response.TodoResponse;
import com.example.wetoo.response.UserInfoResponse;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {

    @POST("login")      // 로그인
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
    );

    @POST("register")   // 회원가입
    Call<Void> register(
            @Body RegisterRequest registerRequest
    );

    @GET("user")    // 내 정보 조회
    Call<MyInfoResponse> MyInfo(
            @Header("Authorization") String token
    );

    @GET("search/{userid}")     // 유저 정보 검색
    Call<SearchResponse> search(
            @Header("Authorization") String token,
            @Path("userid") String userid
    );

    @POST("todo")   // 투두 추가
    Call<BoardResponse> board(
            @Header("Authorization") String token,
            @Body BoardRequest boardRequest
    );


    @GET("todo/list/user/{id}")     // 유저 투두리스트
    Call<ArrayList<TodoResponse>> todo(
            @Header("Authorization") String token,
            @Path("id") long id,
            @Query("todo-year-month") String todoyearmonth
    );


    @GET("todo/list")   // 내 투두리스트
    Call<ArrayList<MyTodoResponse>> myTodo(
            @Header("Authorization") String token,
            @Query("todo-year-month") String todoyearmonth
    );


    @GET("todo/{id}")   // 투두 상세 보기
    Call<DetailResponse> detail(
            @Header("Authorization") String token,
            @Path("id") long id
    );

    @PATCH("todo/{id}")
    Call<EditResponse> edit(
            @Header("Authorization") String token,
            @Path("id") long id,
            @Body EditRequest editRequest
    );

    @DELETE("todo/{id}")
    Call<Void> delete(
            @Header("Authorization") String token,
            @Path("id") long id
    );

    @PUT("todo/{id}")
    Call<Void> success(
            @Header("Authorization") String token
    );

    @PUT("todo/{id}/like")
    Call<Void> like(
            @Header("Authorization") String token,
            @Path("id") long id
    );

    @GET("user/{id}")
    Call<UserInfoResponse> userInfo(
            @Header("Authorization") String token,
            @Path("id") String id
    );
}
