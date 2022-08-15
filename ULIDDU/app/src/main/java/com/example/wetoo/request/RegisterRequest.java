package com.example.wetoo.Request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    private final String name;

    @SerializedName("age")
    private final int age;

    @SerializedName("user-id")
    private final String NewId;

    @SerializedName("password")
    private final String NewPw;

    public RegisterRequest(String name, int age, String newId, String newPw) {
        this.name = name;
        this.age = age;
        NewId = newId;
        NewPw = newPw;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNewId() {
        return NewId;
    }

    public String getNewPw() {
        return NewPw;
    }
}
