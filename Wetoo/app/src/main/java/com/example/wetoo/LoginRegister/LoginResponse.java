package com.example.wetoo.LoginRegister;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user-id")
    private String userId;

    @SerializedName("access-token")
    private String accessToken;

    @SerializedName("refresh-token")
    private String refreshToken;

    public LoginResponse(String userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
