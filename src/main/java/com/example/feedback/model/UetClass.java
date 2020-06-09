package com.example.feedback.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fb_Class")
public class UetClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String displayName;

    @NotNull
    private String giangVien;

    private int countRate = 0;

    private double rateAverage = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
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
