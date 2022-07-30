package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class MyTodoResponse {
    private long id;
    private String title;

    @SerializedName("todo-date")
    private String tododate;

    private Boolean iscompleted;
    private Boolean isliked;

    public MyTodoResponse(long id, String title, String tododate, Boolean iscompleted, Boolean isliked) {
        this.id = id;
        this.title = title;
        this.tododate = tododate;
        this.iscompleted = iscompleted;
        this.isliked = isliked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTododate() {
        return tododate;
    }

    public void setTododate(String tododate) {
        this.tododate = tododate;
    }

    public Boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public Boolean getIsliked() {
        return isliked;
    }

    public void setIsliked(Boolean isliked) {
        this.isliked = isliked;
    }
}
