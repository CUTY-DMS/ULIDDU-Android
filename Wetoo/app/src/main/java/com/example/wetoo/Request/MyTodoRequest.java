package com.example.wetoo.Request;

import com.google.gson.annotations.SerializedName;

public class MyTodoRequest {

    @SerializedName("todo-year-month")
    private String todoyearmonth;

    public MyTodoRequest(String todoyearmonth) {
        this.todoyearmonth = todoyearmonth;
    }

    public String getTodoyearmonth() {
        return todoyearmonth;
    }
}
