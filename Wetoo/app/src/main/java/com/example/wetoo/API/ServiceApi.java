package com.example.wetoo.API;

import com.example.wetoo.Request.LoginRequest;
import com.example.wetoo.Response.LoginResponse;
import com.example.wetoo.Request.RegisterRequest;
import com.example.wetoo.Response.MyInfoResponse;
import com.example.wetoo.Request.BoardRequest;
import com.example.wetoo.Request.EditRequest;
import com.example.wetoo.Request.TodoRequest;
import com.example.wetoo.Response.DetailResponse;
import com.example.wetoo.Response.EditResponse;
import com.example.wetoo.Response.MyTodoResponse;
import com.example.wetoo.Response.SearchResponse;
import com.example.wetoo.Response.TodoResponse;
import com.example.wetoo.Response.UserInfoResponse;

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

public interface ServiceApi {

    @POST("login")
        // 로그인
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
    Call<Void> board(
            @Header("Authorization") String token,
            @Body BoardRequest boardRequest
    );


    @HTTP(method = "GET", path = "todo/list/user/{id}", hasBody = true)
    Call<List<TodoResponse>> todo(
            @Header("Authorization") String token,
            @Path("id") long id,
            @Body TodoRequest todoRequest
    );

    @HTTP(method = "GET", path = "todo/list", hasBody = true)   // 내 투두리스트
    Call<List<MyTodoResponse>> myTodo(
            @Header("Authorization") String token,
            @Body TodoRequest todoRequest
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

    @GET("user/{id}")
    Call<UserInfoResponse> userInfo(
            @Header("Authorization") String token,
            @Path("id") String id
    );
}
