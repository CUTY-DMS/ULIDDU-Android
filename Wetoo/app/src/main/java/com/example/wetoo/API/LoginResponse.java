package com.example.wetoo.API;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user-id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
