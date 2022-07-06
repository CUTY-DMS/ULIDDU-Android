package com.example.wetoo.API;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private int age;

    @SerializedName("user-id")
    private String NewId;

    @SerializedName("password")
    private String NewPw;

    public RegisterRequest(String name, String NewPw, String NewId, int age) {
        this.name = name;
        this.age = age;
        this.NewId = NewId;
        this.NewPw = NewPw;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNewId(String newId) {
        NewId = newId;
    }

    public void setNewPw(String newPw) {
        NewPw = newPw;
    }
}
