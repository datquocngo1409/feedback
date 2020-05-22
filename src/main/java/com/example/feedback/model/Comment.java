package com.example.feedback.model;

import javax.persistence.*;

@Entity
@Table(name = "fb_Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Comment() {
    }

    public Comment(int uetClassId, String content, String username) {
        this.uetClassId = uetClassId;
        this.content = content;
        this.username = username;
    }

    private int uetClassId;

    private String content;

    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUetClassId() {
        return uetClassId;
    }

    public void setUetClassId(int uetClassId) {
        this.uetClassId = uetClassId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
