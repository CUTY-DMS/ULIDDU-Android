package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {
    private String name;

    @SerializedName("user-id")
    private String userId;

    private String age;

    public UserInfoResponse(String name, String userId, String age) {
        this.name = name;
        this.userId = userId;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getAge() {
        return age;
    }
}
