package com.example.wetoo.Request;

import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class LoginRequest {

    @SerializedName("user-id")
    String userId;

    @SerializedName("password")
    String userPw;

    public LoginRequest(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public String getUserId() {
        return userId;
    }
    public String getUserPw() {
        return userPw;
    }
}
