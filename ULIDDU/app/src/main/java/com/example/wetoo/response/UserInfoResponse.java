package com.example.wetoo.response;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {
    private String name;

    @SerializedName("user-id")
    private String userid;

    private int age;

    public UserInfoResponse(String name, String userid, int age) {
        this.name = name;
        this.userid = userid;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUserid() {
        return userid;
    }

    public int getAge() {
        return age;
    }
}
