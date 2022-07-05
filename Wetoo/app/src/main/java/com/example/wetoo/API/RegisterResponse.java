package com.example.wetoo.API;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("user-id")
    private String userId;

    public String getUserId() {
        return userId;
    }
}
