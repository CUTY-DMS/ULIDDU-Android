package com.example.wetoo.Board;

public class Post {
    private String userId;
    private String title;
    private String date;

    public Post(String userId, String title, String date) {
        this.userId = userId;
        this.title = title;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
