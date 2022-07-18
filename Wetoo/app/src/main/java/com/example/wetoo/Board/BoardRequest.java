package com.example.wetoo.Board;

public class BoardRequest {
    private String title;
    private String content;
    private String ispublic;

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
