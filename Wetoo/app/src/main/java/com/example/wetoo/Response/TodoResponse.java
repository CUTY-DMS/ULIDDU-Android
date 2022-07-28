package com.example.wetoo.Response;

import com.google.gson.annotations.SerializedName;

public class TodoResponse {
    private String id;
    private String title;

    @SerializedName("todo-date")
    private String todoDate;

    private Boolean iscompleted;
    private Boolean isliked;

    public TodoResponse(String id, String title, String todoDate, Boolean iscompleted, Boolean isliked) {
        this.id = id;
        this.title = title;
        this.todoDate = todoDate;
        this.iscompleted = iscompleted;
        this.isliked = isliked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
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
