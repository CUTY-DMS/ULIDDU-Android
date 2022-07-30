package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access-token")
    private String accessToken;

    @SerializedName("refresh-token")
    private String refreshToken;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
