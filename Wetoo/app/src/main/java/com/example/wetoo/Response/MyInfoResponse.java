package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class MyInfoResponse {

    @SerializedName("name")
    private String userName;

    @SerializedName("user-id")
    private String userId;

    @SerializedName("age")
    private int userAge;

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserAge() {
        return userAge;
    }
}
