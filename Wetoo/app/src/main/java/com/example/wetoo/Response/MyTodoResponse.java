package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class MyTodoResponse {
    private String id;
    private String title;

    @SerializedName("todo-date")
    private String tododate;

    private Boolean iscompleted;
    private Boolean isliked;

    public MyTodoResponse(String id, String title, String tododate, Boolean iscompleted, Boolean isliked) {
        this.id = id;
        this.title = title;
        this.tododate = tododate;
        this.iscompleted = iscompleted;
        this.isliked = isliked;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTododate() {
        return tododate;
    }

    public Boolean getIscompleted() {
        return iscompleted;
    }

    public Boolean getIsliked() {
        return isliked;
    }
}
