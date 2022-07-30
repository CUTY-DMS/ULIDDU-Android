package com.example.wetoo.Request;

import com.google.gson.annotations.SerializedName;

public class TodoRequest {

    @SerializedName("todo-year-month")
    private String todoyearmonth;

    public TodoRequest(String todoyearmonth) {
        this.todoyearmonth = todoyearmonth;
    }

    public String getTodoyearmonth() {
        return todoyearmonth;
    }
}
