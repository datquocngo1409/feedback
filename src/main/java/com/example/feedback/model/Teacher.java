package com.example.feedback.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "fb_Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String age;

    private String phoneNumber;

    @Email
    private String email;

    private String avatarUrl;

    private int countRate = 0;

    private double rateAverage = 0;

    public Teacher() {
    }

    public Teacher(String name, String age, String phoneNumber, @Email String email, String avatarUrl) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public Teacher(String name, String age, String phoneNumber, @Email String email, String avatarUrl, int countRate, double rateAverage) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.countRate = countRate;
        this.rateAverage = rateAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getCountRate() {
        return countRate;
    }

    public void setCountRate(int countRate) {
        this.countRate = countRate;
    }

    public double getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(double rateAverage) {
        this.rateAverage = rateAverage;
    }
}
