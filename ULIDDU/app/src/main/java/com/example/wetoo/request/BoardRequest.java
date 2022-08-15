package com.example.wetoo.request;

import com.google.gson.annotations.SerializedName;

public class BoardRequest {
    private String title;
    private String content;
    private Boolean ispublic;

    @SerializedName("todo-date")
    private String tododate;

    public BoardRequest(String title, String content, Boolean ispublic, String tododate) {
        this.title = title;
        this.content = content;
        this.ispublic = ispublic;
        this.tododate = tododate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public String getTododate() {
        return tododate;
    }
}
