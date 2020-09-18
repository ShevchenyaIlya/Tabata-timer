package com.example.tabatatimer;

public class SequenceModel {
    private int activityImage;
    private String title;
    private String baseValue;

    public SequenceModel(int activityImage, String title, String baseValue) {
        this.activityImage = activityImage;
        this.title = title;
        this.baseValue = baseValue;
    }

    public int getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(int activityImage) {
        this.activityImage = activityImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }
}
