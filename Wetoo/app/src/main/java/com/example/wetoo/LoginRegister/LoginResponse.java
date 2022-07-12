package com.example.wetoo.LoginRegister;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user-id")
    private String userId;

    public String getUserId() {
        return userId;
    }
}
