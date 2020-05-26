package com.example.feedback.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fb_Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Comment() {
    }

    public Comment(int uetClassId, String content, String username, int ratingValue) {
        this.uetClassId = uetClassId;
        this.content = content;
        this.username = username;
        this.ratingValue = ratingValue;
    }

    private int uetClassId;

    private String content;

    private String username;

    int ratingValue;

    Date lastUpdateTime;

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

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
