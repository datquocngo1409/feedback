package com.example.feedback.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fb_TeacherComment")
public class TeacherComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int teacherId;

    private String content;

    private String username;

    private String name;

    private String avatarUrl;

    int ratingValue;

    Date lastUpdateTime;

    public TeacherComment() {
    }

    public TeacherComment(int teacherId, String content, String username, String name, String avatarUrl, int ratingValue, Date lastUpdateTime) {
        this.teacherId = teacherId;
        this.content = content;
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.ratingValue = ratingValue;
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
