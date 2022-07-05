package com.example.wetoo.API;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private String userId;
    private int code;

    public String getUserId() {
        return userId;
    }

    public int getCode() {
        return code;
    }
}
