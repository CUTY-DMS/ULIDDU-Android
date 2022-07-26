package com.example.wetoo.Board;

import android.widget.Switch;

import com.google.gson.annotations.SerializedName;

public class BoardRequest {
    private String title;
    private String content;
    private String ispublic;

    @SerializedName("todo-date")
    private String todoDate;

    public BoardRequest(String title, String content, String ispublic) {
        this.title = title;
        this.content = content;
        this.ispublic = ispublic;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getIspublic() {
        return ispublic;
    }
}
